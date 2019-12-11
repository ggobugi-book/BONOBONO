#!/usr/bin/env python
# coding: utf-8

import pandas
from konlpy.tag import Komoran
from gensim.models import word2vec
from nltk.corpus import stopwords
from nltk.tokenize import word_tokenize
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.feature_extraction.text import CountVectorizer
import string
import re
import sys


# java에서 넘어오는 argument 값들
# print(sys.argv[1]) # book_title
# print(sys.argv[2]) # page_num
# 1page -> 350


book_title = 'F:/IT/JAVA/workspace/AndroidWeb/' + sys.argv[1] + '.txt'




#파일 읽어오기
file = open(book_title,'r',encoding='utf-8')
line_raw = file.read()
page_num = int(sys.argv[2])*350
line = line_raw[:page_num]

#장(chapter)마다 나눔
sentences = re.split('(?<=[2-9]\n)|(?<=[0-9][0-9]\n)|(?<=[0-9][0-9][0-9]\n)', line)


#Komoran 형태소 분석을 위한 전처리
sent=[]
for stuff in sentences:
#         stuff = stuff.replace("'","").replace("?","").replace(".","").replace("'","").replace('"'
# ,"").replace("!","").replace("-","").replace('"',"").replace("\n","")
#         stuff = re.sub("\n","", line).strip()
        stuff= "\n".join([s for s in stuff.split("\n") if s])
        sent.append(stuff)


# In[3]:


# print(sent)


# In[4]:


komoran = Komoran(userdic='F:/IT/JAVA/workspace/AndroidWeb/user_dic.txt')

#형태소분석
sentences_tag = []
for sentence in sent:
    morph = komoran.pos(sentence)
    sentences_tag.append(morph)

#명사 추출
noun_adj_list=[]
noun_adj=[]

for sentence1 in sentences_tag:
    noun_adj=[]
    for word, tag in sentence1:
        if len(word)>1:
            if tag in ['NNP']: #komoran
                word = word.replace(" ","")
                noun_adj.append(word)
                
    noun_adj_list.append(noun_adj)

#만약 chapter가 하나일때 TF-IDF 분석오류남으로 조건처리

if len(sentences) < 2 :
    noun_adj_list = noun_adj


############# TF-IDF###################

# 데이터 전처리
ad_text=[]
b=''
for i in noun_adj_list:
    a=" ".join(i).split(",")
    b=''.join(a)
    ad_text.append(b)

# TF
# vector = CountVectorizer()
# print(vector.fit_transform(ad_text).toarray()) # 코퍼스로부터 각 단어의 빈도 수를 기록한다.
# print(vector.vocabulary_) # 각 단어의 인덱스가 어떻게 부여되었는지를 보여준다.


# TF-IDF 설정
tfidf = TfidfVectorizer(max_features = 7, max_df=0.95, min_df=0) #(max_features = 5) <- 상위 5개만 추출

#generate tf-idf term-document matrix
A_tfidf_sp = tfidf.fit_transform(ad_text)

tfidf_dict = tfidf.get_feature_names()
print(tfidf_dict)

# 값이 적을수록 다른 문서에서 자주 언급
print(tfidf.vocabulary_)


#############Word2Vec######################

# Word2Vec 을 위한 전처리
sents = [doc.split(" ") for doc in ad_text]


model = word2vec.Word2Vec(sents, size=200,window=100,hs=1,min_count=2,sg=1)

# print(list(model.wv.vocab.keys()))
# print("vocab length : %d"%len(model.wv.vocab))


wv = model.wv #Word2Vec model
del(model)
# 전달받은 sigwords 중 model에 포함된 단어를 우선순위 순서로 추려냄
sigvocs = []
missvocs = []
hit = 0
miss = 0
for worddic in tfidf_dict:
    try:
        word = worddic
        if wv.vocab[word]:
            sigvocs.append(word)
            hit += 1
        if hit >= length:
            break
    except:
        missvocs.append(word)
        miss += 1
# 디버깅용. 추후 삭제.
# print("missed: %d"  % miss)
# for voc in missvocs:
#     print(voc)2


# 디스턴스 매트릭스를 데이터프레임 형태로 만듦
distDF =  pandas.DataFrame(columns = sigvocs , index = sigvocs)
for i in sigvocs:
    dists = []
    for j in sigvocs:
        dist = round(wv.distance(i, j), 6)
        dists.append(0 if dist < 1.0e-2 else dist)
    distDF[i] = dists
# print(distDF)



# Word2Vec 검출된 단어와 연관 단어 추출

for i in sigvocs:
    print(wv.most_similar(positive=i, negative=[]))
    


# 단어 조합
from itertools import combinations
test = list(combinations(sigvocs, 2))


for i in test:
    i = str(i)
    i = i.replace("(","").replace(")","").replace("'","").replace(" ","").split(",")
    print((i))
    print(wv.most_similar(positive=i, negative=[]))


############################################
