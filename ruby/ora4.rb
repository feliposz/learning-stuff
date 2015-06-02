require 'oci8'
require 'pp'

db_user = 'HR'
db_pass = 'HR'
db_conn = 'XE'

conn = OCI8.new(db_user, db_pass, db_conn)

# Checking the type of statements

# Array of SQL statements to try
sql_list = [ 'insert into employees values (:employee_id, :first_name, :last_name, :email, :phone_number, ' +
               ':hire_date, :job_id, :salary, :commission_pct, :manager_id, :department_id)',
             'select * from employees where employee_id = :1',
             'update employees set first_name = :1 where employee_id = :2',
             'delete from employees where employee_id = :1',
             'create table tmp_table (x number(3))',
             'alter table tmp_table modify (x number(5))',
             'drop table tmp_table',
             'begin :1 := :2 * 2; end;',
             'declare v_test number := :in; begin :out := v_test * 2; end;' ]

sql_list.each do |sql|

  # Parse a statement and check its type
  cmd = conn.parse(sql)
  puts "### Type: #{cmd.type} SQL: #{cmd.statement}"
  puts

  case cmd.type

    when OCI8::STMT_INSERT
      puts "Do insert"
      # Example of binding parameters by name
      cmd.bind_param(:employee_id, 999)
      cmd.bind_param(:first_name, 'Kevin')
      cmd.bind_param(:last_name, 'Bacon')
      cmd.bind_param(:email, 'KBACON')
      cmd.bind_param(:phone_number, '987.654.3210')
      cmd.bind_param(:hire_date, '27/03/2015')
      cmd.bind_param(:job_id, 'SH_CLERK')
      cmd.bind_param(:salary, 3000)
      cmd.bind_param(:commission_pct, nil, Float) # Must specify the field type when the value is null/nil
      cmd.bind_param(:manager_id, 124)
      cmd.bind_param(:department_id, 50)
      cmd.exec
      puts "Inserted lines: #{cmd.row_count}"

    when OCI8::STMT_SELECT
      puts "Do select"
      # Example of simple select statement with only one parameter and fetching the result
      cmd.exec(999)
      while rec = cmd.fetch
        puts rec.join(", ")
      end
      puts "Rows selected: #{cmd.row_count}"

    when OCI8::STMT_UPDATE
      puts "Do update"
      # Example of update statement with positional parameters
      cmd.bind_param(1, 'Veggie')
      cmd.bind_param(2, 999)
      cmd.exec
      puts "Updated lines: #{cmd.row_count}"

    when OCI8::STMT_DELETE
      puts "Do delete"
      cmd.exec(999)
      puts "Deleted lines: #{cmd.row_count}"

    when OCI8::STMT_CREATE
      puts "Do create"
      cmd.exec
      puts "Table created"
      # Executing a DDL statement and describing an object
      desc = conn.describe_table('tmp_table', true)
      puts "Number of columns: #{desc.num_cols}"
      puts "Columns description:"
      pp desc.columns

    when OCI8::STMT_ALTER
      puts "Do alter"
      cmd.exec
      puts "Table modified"
      desc = conn.describe_table('tmp_table', true)
      pp desc.columns

    when OCI8::STMT_DROP
      puts "Do drop"
      cmd.exec
      puts "Table dropped"

    when OCI8::STMT_BEGIN
      puts "Do begin block"
      # Binding params by position in anonymous block without declare section
      cmd.bind_param(1, nil, Fixnum); # Could be set to any value also, since it's used only for output.
      cmd.bind_param(2, 123);
      puts "Values before block execution: [1] = #{cmd[1]} [2] = #{cmd[2]}"
      cmd.exec
      puts "Values after block execution: [1] = #{cmd[1]} [2] = #{cmd[2]}"

    when OCI8::STMT_DECLARE
      puts "Do declare block"
      # Binding params by name in anonymous block with declare section
      cmd.bind_param(:in, 123);
      cmd.bind_param(:out, nil, Fixnum);
      puts "Values before block execution: [:in] = #{cmd[:in]} [:out] = #{cmd[:out]}"
      cmd.exec
      puts "Values after block execution: [:in] = #{cmd[:in]} [:out] = #{cmd[:out]}"

  end

  puts

end

puts "Commiting changes"
conn.commit

puts "Logging out"
conn.logoff
