class MarkupCalculator

	@@base = 0.05
	@@perPerson = 0.012
	@@defaultNumberPeople = 1
	
	def estimate(initialAmount)
		baseMarkupAmount = initialAmount + calculateBaseMarkup(initialAmount)
		baseMarkupAmount + calculatePersonMarkup(baseMarkupAmount, @@defaultNumberPeople)
	end
	
	def calculateBaseMarkup(amount)
		@@base * amount
	end
	
	def calculatePersonMarkup(amount, numberOfPeople)
		if numberOfPeople < 0
			0
		else
			amount * @@perPerson * numberOfPeople
		end
	end
	
	def setBaseMarkup(percent)
		@@base = percent / 100.0
	end
	
	def getBaseMarkup
		@@base
	end
	
	def setPerPersonMarkup(percent)
		@@perPerson = percent / 100.0
	end
	
	def getPerPersonMarkup
		@@perPerson
	end
	
end

