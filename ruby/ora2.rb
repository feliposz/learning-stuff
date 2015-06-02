require 'conn8'

db_user = 'HR'
db_pass = 'HR'
db_conn = 'XE'

conn = conn8.new(db_user, db_pass, db_conn)

sql = 'select * from employees where rownum <= :1'
n = 1

conn.exec(sql, 3) do |record|
  puts 'Record: ' + n.to_s
  record.each do |column|
    puts '  > ' + column.to_s
  end
  n += 1
end

conn.logoff
