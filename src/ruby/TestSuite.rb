require_relative 'MarkupCalculator'

class TestSuite

	def initialize
		@calculator = MarkupCalculator.new
		@calculator.setBaseMarkup(10)
		@totalTests = 0
		@results = []
	end

	def run_all
		testnum = 0
		@results[testnum+=1] = calculateBaseMarkup_ExactDollarResult
		@results[testnum+=1] = calculateBaseMarkup_ExactCents
		@results[testnum+=1] = testSetBaseMarkup
		@totalTests = testnum
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
	
	def testSetBaseMarkup
		0.1 == @calculator.getBaseMarkup
	end
	
	def calculateBaseMarkup_ExactDollarResult
		10 == @calculator.calculateBaseMarkup(100)
	end
	
	def calculateBaseMarkup_ExactCents
		10.5 == @calculator.calculateBaseMarkup(105)
	end
	
end


tests = TestSuite.new
tests.run_all
