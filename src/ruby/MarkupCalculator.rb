class MarkupCalculator

	@@base = 0.05
	
	def calculate(initialAmount)
		initialAmount + (@@base * initialAmount)
	end
	
end

