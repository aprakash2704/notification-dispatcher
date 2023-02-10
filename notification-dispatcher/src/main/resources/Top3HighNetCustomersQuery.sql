/* This query will
	1. Join the customer_master and account_master tables on the customer_id field. 
 	2. Group the records by customer_id and sum the balance of each customer's accounts to 
 	   get the total net worth. 
 	3. Finally, order the result by the net worth in descending order and limit the result to the top 3
 	   customers.  */
SELECT
  customer.customer_name,
  SUM(acct.account_balance) AS net_worth
FROM
  customer_master customer
  JOIN account_master acct ON customer.customer_id = acct.customer_id
GROUP BY
  customer.customer_id
ORDER BY
  net_worth DESC
LIMIT
  3
  
 /* Techniques to improve the query performance: 
 	1. 	Indexing - This is a common idea/suggestion that could improve the performance 
	  	when dealing with high data volume.In the customer_master and account_master tables, we can create
	  	an index on the columns used in the join condition and the ordering condition to improve query performance.
		For example, to improve the performance of the query I provided above, an index on the customer_id column in both the customer and account tables:
	 .  CREATE INDEX customer_id_index ON customer_master (customer_id);
		CREATE INDEX account_customer_id_index ON account_master (customer_id);
		This will allow the database to quickly look up the rows that match the join condition and the ordering condition 
		without having to scan the entire table
		One disadvantage is indexes can occupy disk space and maintenance overhead, so better to take this into account
		when deciding which column to index.
	
	2.  Caching - we could use this technique to store the result of frequently uses queries in memory.
		Thus it reduces the number of times the query needs to be executed and processed.
		There are different types of caching, to say generically: In-memory database, Application level caching
		database query caching. While choosing this technique, the memory management should be considered else
		it could introduce additional complexity to the system
		
	3.  Partitioning - divide larger tables to smaller, for example in our scenario:
	 	partition the account_master table by the customer_id column. This would mean that each customer's accounts
	 	would be stored in a separate partition and queries that filter data based on the customer_id column 
	 	would only have to scan the relevant partition(s) instead of the entire table. By doing this way
	 	it reduces the amount of data that the database needs to scan in order to execute/process a query. 
	 	Could help especially large tables with high volume data. Down-side is it may require additional
	 	administration but this could be weighed against the potential benefits it bring to the performance.
	 	
	4.  Materialized views - another way of storing the results(large) into a table, these are pre-computed views
		of a database instead of re-executing the query each time.In our scenario:
		creating the materialized view for top_3_customers stores the result of a query that calculates the total balance 
		for each customer and returns the top 3 customers with the highest total balance. 
		This can lead to a significant improvement in query performance, especially for high volume queries
		Down-side is updating the materialized view can be an expensive operation and may impact query performance.
		
	5.  Another technique is de-normalization where the data is duplicated and stored in the same
		table, for ex: we could add a column to the customer_master table that stores the 
		total balance for all of the customer's accounts, avoiding the need to join the customer_master 
		and account_master tables to calculate this value. Down side is de-normalization can increase data redundancy
		and additionally can introduce complexity in maintenance. 
 */
