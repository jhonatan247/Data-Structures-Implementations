module PromotionType
	PRECENT = 0
end
module PaymentMethot
	CASH = 1
	CREDIT = 2
end
module ProductUnit
	PACKAGE =0
	UNIT = 1
	WEIGHT = 2
end
module Status
	ACTIVE =1
	INACTIVE = 2
	DELETED = 3
	CANCELLED = 4
	BUSY = 5
	DEFEATED = 6
end

class Promotion
	attr_accessor :name, :type, :initDate, :endDate, :status, :params


	def inicialize name, type, endDate, params
		@name = name
		@type = type
		@endDate = endDate
		@initDate = Time.now
		@status = Status::ACTIVE
		@params = params
	end

	def apply quantity, price
		if status = Status::ACTIVE
			if endDate <= Time.now
				case @type
				when PromotionType::PRECENT
					return price*quantity*@params[:percent]/100
				else 
					return 0
				end
					
			else
				status = Status::DEFEATED
			end
		end
	end
end

class Stock
	attr_accessor :historyCost, :historyPrice, :currentStock
	def inicialize cost, price, currentStock
		@currentStock = currentStock
		@historyCost = [cost]
		@historyPrice = [price]
	end
	def currentCost
		historyCost[-1]
	end
	def currentCost= val
		historyCost<<val;
	end
	def totalCost?
		currentCost*@currentStock
	end
	def currentPrice
		historyPrice[-1]
	end
	def currentPrice= val
		historyPrice<<val
	end
	def totalPrice?
		currentPrice*@currentStock
	end

end

class Product
	attr_accessor :name, :unit, :stock, :promotions, :status
	def inicialize name, unit, stock
		@name = name
		@unit = unit
		@stock = stock
		@status = Status::ACTIVE
		@promotions = []
	end
	def getTotalPromotions quantity
		promos = 0
		@promotions.each do |promo|
			promos += promo.apply(quantity, @stock.currentPrice)
		end
		promos
	end
	def getPriceStock
		@stock.currentPrice*@stock.currentStock - getTotalPromotions(@stock.currentStock)
	end
	def applyPromotions quantity
		@stock.currentPrice*quantity - promos
	end
end

class Client
	attr_accessor :name, :id, :status
	def inicialize name, id
		@name = name
		@id = id
		@status = Status::ACTIVE
	end
end

class CartProduct
	attr_accessor :product, :quantity
	def inicialize product, quantity
		@product = product
		@quantity = quantity
	end
	def inicialize product
		@product = product
		@quantity = 1
	end
end

class Cart
	attr_accessor :client, :products, :status
	def inicialize client
		@client = client
		@products = []
		@status = Status::ACTIVE
	end

	def addProduct product, quantity
		if @products.include? product
			
		end
	end

end

class Vent
	attr_accessor :cart, :price, :paymentMethot, :date, :status
	def inicialize cart, price, paymentMethot
		@cart = cart
		@price = price
		@paymentMethot = paymentMethot
		@date = Time.now
		@status = Status::ACTIVE
	end
end

class Box
	attr_accessor :currentSeller, :vents, :status
	def inicialize currentSeller
		@currentSeller = currentSeller
		@vents = []
		@status = Status::ACTIVE
	end
	def getClientData
		puts 'The client is registered?'
		puts '1) yes'
		puts '2) no'
		puts 'other) cancel'
	end
	def sell
		if @status == Status::ACTIVE
			@status = Status::BUSY
			Client currentClient = getClientData

			@status = Status::ACTIVE
		end
	end
end

class Supermarket
	attr_accessor :name, :boxes, :products
	def inicialize name, box
		@name = name
		@boxes = [box]
		@products = []
	end
	def addBox seller
		@boxes<< Box.new(seller)
	end
	def activeBox index seller
		@boxes[index].status= Status::ACTIVE
		@boxes[index].currentSeller = seller 
	end
	def dislabeBox index
		@boxes[index].status= Status::INACTIVE
	end
	def removeBox index
		@boxes[index].status= Status::DELETED
	end
	def selectActiveBox
		@Boxes.each do |b|
			return b if b.status== Status::ACTIVE
		end
	end
end
end

kata =Supermarket.new "kata", Box.new "Jaime"

while true
 
	puts 'Welcome to main menu'
	puts '1) Sell'
	puts '2) Manage boxes'
	puts '3) Manage stock'
	puts 'other) Exit'
	select = gets.chomp.to_s
	case select
	when '1'
		
	when '2'
		
	when '3'
		
	else
		break;
	end
end















