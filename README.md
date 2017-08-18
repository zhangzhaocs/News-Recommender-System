# News-Recommender-System
a news recommender system, using item-item collaborative filtering and FunkSVD algorithm

Users can import the project(News-Recommender-System/News-Recommender-System) to Eclipse.

Data directory contains the browsing history of 10,000 users in one month,
each line contains 5 fields: user id \t new id \t browsing time \t title of the news \t content of the news.

We split the data into 2 parts, using the first 20 days' data as the training data while the last 11 days' data as test data.
The task is to recommend news to the 10,000 users in the last 11 days.

To get the recommendation list, run src\Recommender\Recommender.java.

Feel free to contact me(zhangz@ics.ict.ac.cn) if you have any questions.

Respect!
