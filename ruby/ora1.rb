

require 'oci8'

db_user = 'HR'
db_pass = 'HR'
db_conn = 'XE'

# Connect to Oracle database
oci = OCI8.new(db_user, db_pass, db_conn)

# Run a simple query with a bind variable
sql = 'select * from all_users where username = :1'

oci.exec(sql, 'HR') do |record|
  puts record.join(', ')
end
