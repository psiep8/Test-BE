SELECT users,
       years,
       SUM(sales) AS sum_sales,
       sum_sales - ((SELECT SUM(sales)
                      from test3table table2
                      WHERE table2.users = table1.users
                        AND table2.years = table1.years - 1), 0) AS decr_incr
FROM test3table table1
GROUP BY years, users

-- con 0 AND table2.years=table1.years - 1), 0) AS decr_incr
-- con null AND table2.years=table1.years - 1), null) AS decr_incr
