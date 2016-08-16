require_relative 'MarkupCalculator'

class TestSuite

	def initialize
		@calculator = MarkupCalculator.new
		@calculator.setBaseMarkup(10)
		@calculator.setPerPersonMarkup(1.5)
		@totalTests = 0
		@results = []
	end

	def run_all
		testnum = -1
		@results[testnum+=1] = ['calculateBaseMarkup_ExactDollarResult', calculateBaseMarkup_ExactDollarResult]
		@results[testnum+=1] = ['calculateBaseMarkup_ExactCents', calculateBaseMarkup_ExactCents]
		@results[testnum+=1] = ['testSetBaseMarkup', testSetBaseMarkup]
		@results[testnum+=1] = ['testSetPerPersonMarkup', testSetPerPersonMarkup]
		@results[testnum+=1] = ['calculatePersonMarkup_NoPeople_NoMarkup', calculatePersonMarkup_NoPeople_NoMarkup]
		@results[testnum+=1] = ['calculatePersonMarkup_NegativePeople_TreatAsZero', calculatePersonMarkup_NegativePeople_TreatAsZero]
		@results[testnum+=1] = ['calculatePersonMarkup_OnePerson', calculatePersonMarkup_OnePerson]
		@results[testnum+=1] = ['calculatePersonMarkup_ThreePeople', calculatePersonMarkup_ThreePeople]
		@results[testnum+=1] = ['testEstimate_DefaultNumberPeople', testEstimate_DefaultNumberPeople]
		@totalTests = testnum + 1
		reportResults
	end
	
	def reportResults
		failed = 0
		@results.each do |aresult|
			if !aresult[1]
				failed += 1 
				puts 'Failed: ' + aresult[0]
			end
		end
		puts 'Total tests = ' + @totalTests.to_s + ', Passes = ' + (@totalTests - failed).to_s + ', Fails = ' + failed.to_s
	end

	def testEstimate_DefaultNumberPeople
		100 + 10 + (110 * 0.015) == @calculator.estimate(100)
	end
		def testSetBaseMarkup
		0.1 == @calculator.getBaseMarkup
	end
	
	def testSetPerPersonMarkup
		0.015 == @calculator.getPerPersonMarkup
	end
	
	def calculateBaseMarkup_ExactDollarResult
		10 == @calculator.calculateBaseMarkup(100)
	end
	
	def calculateBaseMarkup_ExactCents
		10.5 == @calculator.calculateBaseMarkup(105)
	end
	
	def calculatePersonMarkup_NoPeople_NoMarkup
		0 == @calculator.calculatePersonMarkup(100, 0)
	end
	
	def calculatePersonMarkup_NegativePeople_TreatAsZero
		0 == @calculator.calculatePersonMarkup(100, -1)
	end
	
	def calculatePersonMarkup_OnePerson
		1.5 == @calculator.calculatePersonMarkup(100, 1)
	end
	
	def calculatePersonMarkup_ThreePeople
		4.5 == @calculator.calculatePersonMarkup(100, 3)
	end
	
end


tests = TestSuite.new
tests.run_all
