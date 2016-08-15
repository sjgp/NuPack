class MarkupCalculator

	@@base = 0.05
	
	def estimate(initialAmount)
		initialAmount + (@@base * initialAmount)
	end
	
	def calculateBaseMarkup(amount)
		@@base * amount
	end
	
	def setBaseMarkup(percent)
		@@base = percent / 100.0
	end
	
	def getBaseMarkup
		@@base
	end
	
end

