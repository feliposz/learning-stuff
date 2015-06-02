import sqlparse

def psql(query):
    print(sqlparse.format(str(query), reindent=True))

