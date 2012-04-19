class StringCalculator
  def add s
    return 0 if s.to_s == ""
    st, delimiters = find_delimiter s
    numbers = st.split(delimiters).map(&:to_i)
    negatives = numbers.select{|n| n < 0}
    raise Exception.new("Tried to add negatives: #{negatives.to_s}") if negatives.any?
    numbers.select{|n| n <= 1000 }.inject(0){|sum,x| sum += x}
  end

  private
    def find_delimiter s
        if (s =~ %r{^//(.+)\n})
            pattern = $& #we grab the full match to remove it from the string
            delimiters = $1.scan(/\[([^\[\]]+)\]/).flatten.compact #parse the delimiters between the brackets
            delimiters = delimiters.map{|d| Regexp.escape(d)}.join("|")
            return s.gsub(pattern,''), Regexp.new(delimiters)
        end
        return s, /,|\n/
    end
end

require 'minitest/spec'
require 'minitest/autorun'

describe StringCalculator do
    before do
        @sc = StringCalculator.new
    end

    describe "when adding regular strings" do
        it "should add the empty string" do
            @sc.add("").must_equal 0
        end
        it "should add comma-separated numbers" do
            @sc.add("1,2,3").must_equal 6
        end
        it "should add comma and carraige-separated numbers" do
            @sc.add("1,2,3\n4").must_equal 10
        end
    end

    describe "when adding string with custom delimiter" do
        it "should use the custom delimiter" do
            @sc.add("//[n][mmmm]\n1n2n3").must_equal 6
        end
        it "should use the custom delimiter even if its a special char" do
            @sc.add("//[*]\n1*2*3").must_equal 6
            @sc.add("//[*][????]\n50*49????1").must_equal 100
        end
        it "should not add the numbers higher than 1000" do
            @sc.add("1,1,1\n1001").must_equal 3
        end
        it "should not allow the adding of negative numbers" do
            lambda{ @sc.add("-1,-2,1") }.must_raise Exception
        end
    end
end
