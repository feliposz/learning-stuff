require 'oci8'

db_user = 'HR'
db_pass = 'HR'
db_conn = 'XE'

conn = OCI8.new(db_user, db_pass, db_conn)

sql = 'select * from employees where rownum <= :1'

# Create a prepared statement
cmd = conn.parse(sql)

# Run a prepared statement with passing a value to the bind variable
cmd.exec(5)

# Get the column names
puts cmd.get_col_names.join(';')

# Fetch the records
while rec = cmd.fetch # same as: cmd.fetch do |rec| ... end
  puts rec.join(';')
end

# Number of rows processed in the statement execution
puts "Rows returned: #{cmd.row_count}"

# Disconnect from the DB
conn.logoff
