require 'securerandom'

SIZE = 100000
STEPS = 10

tab = {}

SIZE.times do |i|
  k = SecureRandom.urlsafe_base64(15)
  tab[k] = SecureRandom.random_number(1000)
end

STEPS.times do |i|
  tab.keys do |k|
    tab[k] += SecureRandom.random_number(1000)
  end
end

min = nil
max = nil
sum = 0

tab.keys.each do |k|
  min = tab[k] if min == nil or tab[k] < min
  max = tab[k] if max == nil or tab[k] > max
  sum += tab[k]
end

avg = sum / tab.size

puts "tab.size = #{tab.size}"
puts "min = #{min}"
puts "max = #{max}"
puts "sum = #{sum}"
puts "avg = #{avg}"

#gets
