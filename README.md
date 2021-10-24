# Microblog Information Retrieval System

| Name | Student Number | Tasks |
| --- | --- | --- |
| Kaishuo Wang | 300068284 | Part 1, Part 3, Part 4 |
| Ralph Huo | 300069921 | Part 2, Part 5 |

### Detailed note about the functionality of this program

This is program is a Information Retrieval system. There is a file called *"Trec_microblog11.txt"* whcih includes raw Twitter messages. 

In part 1, we preprocess these raw messages, and return tokens to be added to the inverted index. Specifically, we first read raw messages from the file, and remove URLs, punctuations, numbers, any other non alphabets, and stop words as well.

In part 2, we built an inverted index, with an entry for each word in the vocabulary. We first use *1+log(tf)* to calculate term frequency. And then we use *log(N/df)* to calculate inverse document frequency.

In part 3, we use the inverted index from step 2 to find documents that contain at least one of the query words, and compute the cosine similarity score between the query and each document. We also did length normalization before we calculate the consine similarity. Finally, before we return these retrievaled documents, we sort them in decreasing oeder of similarity scores.

In part 4, we test this system with a set of queries and save top 1000 results for each query to file *Results.txt*.

### Instruction on how to run this program.

In order to run this program, you only need to run *src/Tests/TestStep4.java*

### Algorithms, data strucutes

As I just mentioned, we use *1+log(tf)* to calculate term frequency, and *log(N/df)* to calculate inverse document frequency. Also, we use consine similarity algorithm to compre query with each document.

As for data structure, we mainly used hashtable to store information. And we also used hashmap to store sorted results.

There are 55376 words after we remove stop words.

### Following is a sample of 100 tokens from our vocabulary:
successor  
zero  
bye  
inspiron  
fondo  
cbo  
cbn  
posso  
kijang  
cbj  
cbi  
merrillvil  
plaster  
srio  
gohsep  
nhlnhlpa  
cbe  
lutang  
makasiiiiw  
nhiabu  
cbc  
cba  
sheepl  
srie  
exorcist  
caz  
cav  
sceneri  
cau  
venuepro  
cat  
norahodonnel  
taqaandan  
car  
turnbul  
cap  
contributornetwork  
cao  
can  
tamper  
cam  
endlord  
cal  
istimpictur  
cai  
cah  
cag  
erwarten  
caf  
cae  
cad  
cab  
caa  
fanmad  
sharnisesobomb  
faithcontinu  
maccabe  
basebal  
yorkvil  
whatthehel  
alban  
sadler  
believ  
wozniak  
gronholm  
cttraffic  
artsi  
valuabl  
dashawai  
mcafe  
sunsentinelcom  
corbinbleu  
takesov  
laahh  
belief  
dreamgrow  
takesom  
flax  
flaw  
belieb  
jerusalem  
flav  
flat  
asamom  
flap  
datjquo  
dayasmil  
mexicohouston  
greydydankei  
porridg  
flag  
zexr  
tippali  
boguski  
rightnow  
modrewrit  
videoalmost  
fatherson  
macharia  
drawn  

### Following is first ten answers to queies 1 and 25
MB001 Q0 30198105513140224 1 0.865 myRun  
MB001 Q0 29552940691759104 2 0.786 myRun  
MB001 Q0 29983478363717633 3 0.758 myRun  
MB001 Q0 30260724248870912 4 0.722 myRun  
MB001 Q0 33823403328671744 5 0.695 myRun  
MB001 Q0 34703780100448257 6 0.688 myRun  
MB001 Q0 29059262076420096 7 0.680 myRun  
MB001 Q0 29486393336008704 8 0.668 myRun  
MB001 Q0 30319208176820224 9 0.664 myRun  
MB001 Q0 29564786882646016 10 0.661 myRun  

MB025 Q0 32527914905894912 1 0.706 myRun  
MB025 Q0 31773184512495616 2 0.647 myRun  
MB025 Q0 33207460051292160 3 0.641 myRun  
MB025 Q0 30117014114668544 4 0.626 myRun  
MB025 Q0 32165530513178625 5 0.617 myRun  
MB025 Q0 31849003209461760 6 0.617 myRun  
MB025 Q0 31823291815567361 7 0.574 myRun  
MB025 Q0 31286354960715777 8 0.552 myRun  
MB025 Q0 32481505913602048 9 0.551 myRun  
MB025 Q0 32534533177872385 10 0.546 myRun  

### Result of part 5
runid                 	all		myRun  
num_q                 	all		49  
num_ret               	all		49000  
num_rel               	all		2640  
num_rel_ret           	all		1957  
map                   	all		0.1494  
gm_map                	all		0.0491  
Rprec                 	all		0.1733  
bpref                 	all		0.1403  
recip_rank            	all		0.3610  
iprec_at_recall_0.00  	all		0.4155  
iprec_at_recall_0.10  	all		0.2588  
iprec_at_recall_0.20  	all		0.2328  
iprec_at_recall_0.30  	all		0.1987  
iprec_at_recall_0.40  	all		0.1759  
iprec_at_recall_0.50  	all		0.1605  
iprec_at_recall_0.60  	all		0.1331  
iprec_at_recall_0.70  	all		0.1083  
iprec_at_recall_0.80  	all		0.0797  
iprec_at_recall_0.90  	all		0.0509  
iprec_at_recall_1.00  	all		0.0143  
P_5                   	all		0.2367  
P_10                  	all		0.2143  
P_15                  	all		0.2054  
P_20                  	all		0.1969  
P_30                  	all		0.1871   
P_100                 	all		0.1441  
P_200                 	all		0.1109  
P_500                 	all		0.0700  
P_1000                	all		0.0399  

### Conclution
* P@10 (from report): 0.2143
* Mean average precision score (MAP): 0.1494
* The runid is what we named as myRun
* num_q there are total 49 queries
* num_ret is 49000 which means the number of ducuments retrievaled over all queries is 49000
* num_rel means the total number of relevant documents over all queries is 2640
* num_rel_ret means the total number of relevant documents retrieved over all queries is 1957
* From prec_at_recall 0.00 to prec_at_recall 1.00 which is starting from 0.4155 decreasing to 0.0143. Which Mean the 0% of the rel docs for a query have been retrieved from 0.4155(taken to be maximum of precision at all recall points >= 0.00) until to the 100% of the rel docs for a query have been retrieved from 0.0143(taken to be maximum of precision at all recall points >= 1.0). Which shows more accurate.
* For the p_5 to p_1000. The precision (percent of retrieved docs that are relevant) after the number of documents (whether relevant or irrelevant) have been retrieved. Values ​​averaged over all queries. If the number of docs were not retrieved for a query, then all missing docs are assumed to be non-relevant. Based on what we got on the trec_eval. We got starting from the first 5 docs which is 0.2367 up to the first 1000 docs which is 0.0399. Which shows the decreasing of the percent of retrieved docs that are relevant. Which is understandable and as we expected.
* In conclusion, the data we have collected from Trec_eval was correct. Although the number from Interpolated Recall and Precision of number first docs are a little bit small. Which means the number is bigger, the data was more accurate. But, the number we got is getting smaller and smaller. Which is ideally acceptable.