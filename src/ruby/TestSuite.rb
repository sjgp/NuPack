require_relative 'MarkupCalculator'

class TestSuite

	def initialize
		@calculator = MarkupCalculator.new
		@totalTests = 1
		@results = []
	end

	def run_all
		testnum = 0
		@results[testnum+=1] = baseMarkupApplied
		reportResults
	end
	
	def reportResults
		passes = 0
		@results.each do |aresult|
			if aresult 
				passes += 1 
			end
		end
		puts 'Total tests = ' + @totalTests.to_s + ', Passes = ' + passes.to_s + ', Fails = ' + (@totalTests - passes).to_s
	end
	
	def baseMarkupApplied
		105 == @calculator.calculate(100)
	end
	
end


tests = TestSuite.new
tests.run_all
