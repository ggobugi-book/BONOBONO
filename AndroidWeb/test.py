
import pandas
from konlpy.tag import Okt
from gensim.models import word2vec
from nltk.corpus import stopwords
from nltk.tokenize import word_tokenize
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.feature_extraction.text import CountVectorizer
import string
import re



# In[3]:



#���� �о����
file = open('F:/IT/JAVA/workspace/AndroidWeb/mujung.txt','r',encoding='UTF-8')
line = file.read()

#��(chapter)���� ����
sentences = re.split('(?<=[2-9]\n)|(?<=[0-9][0-9]\n)|(?<=[0-9][0-9][0-9]\n)', line)

#Komoran ���¼� �м��� ���� ��ó��
sent=[]
for stuff in sentences:
#         stuff = stuff.replace("'","").replace("?","").replace(".","").replace("'","").replace('"'
# ,"").replace("!","").replace("-","").replace('"',"").replace("\n","")
#         stuff = re.sub("\n","", line).strip()
        sent.append(stuff)


# In[4]:


# twitter = Komoran(userdic='./user_dic.txt')
twitter = Okt()


# In[5]:


#���¼Һм�
sentences_tag = []
for sentence in sent:
    morph = twitter.pos(sentence)
    sentences_tag.append(morph)


# In[6]:


#��� ����
noun_adj=[]

#komoran
# for sentence1 in sentences_tag:
#     for word, tag in sentence1:
#         if tag in ['NNP']: 
#             noun_adj.append(word)

#twitter
for sentence1 in sentences_tag:
    for word, tag in sentence1:
        if len(word)>1:
            if tag in ['Noun']: #komoran
                noun_adj.append(word)


# In[7]:


# print(noun_adj)


# In[8]:


############# TF-IDF###################


# In[9]:


# In[10]:


vector = CountVectorizer()
# print(vector.fit_transform(noun_adj).toarray()) # ���۽��κ��� �� �ܾ��� �� ���� ����Ѵ�.
# print(vector.vocabulary_) # �� �ܾ��� �ε����� ��� �ο��Ǿ������� �����ش�.


# In[11]:


tfidfv = TfidfVectorizer().fit(noun_adj)
# print(tfidfv.transform(noun_adj).toarray())
# print(tfidfv.vocabulary_)


# In[12]:


# TF-IDF ����
tfidf = TfidfVectorizer(max_features = 5, max_df=0.95, min_df=0) #(max_features = 5) <- ���� 5���� ����

#generate tf-idf term-document matrix
A_tfidf_sp = tfidf.fit_transform(noun_adj)


# In[13]:


tfidf_dict = tfidf.get_feature_names()
print(tfidf_dict)


# In[14]:


#############Word2Vec######################


# In[15]:


sents = [doc.split(" ") for doc in noun_adj] #Word2Vec �� ���� ��ó��


# In[16]:



model = word2vec.Word2Vec(sents, size=200,window=10,hs=1,min_count=2,sg=1)

# print(list(model.wv.vocab.keys()))
# print("vocab length : %d"%len(model.wv.vocab))

# print(model.wv.most_similar("����"))


# In[17]:


# In[18]:


wv = model.wv #Word2Vec model
# del(model)
# ���޹��� sigwords �� model�� ���Ե� �ܾ �켱���� ������ �߷���
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
# ������. ���� ����.
print("missed: %d"  % miss)
for voc in missvocs:
    print(voc)


# ���Ͻ� ��Ʈ������ ������������ ���·� ����
distDF =  pandas.DataFrame(columns = sigvocs , index = sigvocs)
for i in sigvocs:
    dists = []
    for j in sigvocs:
        dist = round(wv.distance(i, j), 6)
        dists.append(0 if dist < 1.0e-2 else dist)
    distDF[i] = dists
print(distDF)


# In[19]:


# print(distDF)


# In[142]:

result_text=wv.most_similar(positive=['형식','영채'])
print(result_text)