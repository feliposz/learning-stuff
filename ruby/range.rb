
puts "Inclusive: r1 = 1..5"
r1 = 1..5
r1.each do |i|
  puts i
end

puts "Open end: r2 = 1...5"
r2 = 1...5
r2.each do |i|
  puts i
end


puts "Inclusive: r3 = 'a'..'e'"
r3 = 'a'..'e'
r3.each do |i|
  puts i
end

puts "Open end: r4 = 'a'...'e'"
r4 = 'a'...'e'
r4.each do |i|
  puts i
end

