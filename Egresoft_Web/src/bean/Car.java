package bean;

public class Car {

	private String Id;
	private String Brand;
	private int Year;
	private String Color;
	private int Price;
	private boolean SoldState;
	
	public Car(String Id, String Brand, int Year, String Color, int Price,
			boolean SoldState) {
		super();
		this.Id = Id;
		this.Brand = Brand;
		this.Year = Year;
		this.Color = Color;
		this.Price = Price;
		this.SoldState = SoldState;
	}

	public String getId() {
		return Id;
	}

	public void setId(String Id) {
		this.Id = Id;
	}

	public String getBrand() {
		return Brand;
	}

	public void setBrand(String Brand) {
		this.Brand = Brand;
	}

	public int getYear() {
		return Year;
	}

	public void setYear(int Year) {
		this.Year = Year;
	}

	public String getColor() {
		return Color;
	}

	public void setColor(String Color) {
		this.Color = Color;
	}

	public int getPrice() {
		return Price;
	}

	public void setPrice(int Price) {
		this.Price = Price;
	}

	public boolean isSoldState() {
		return SoldState;
	}

	public void setSoldState(boolean SoldState) {
		this.SoldState = SoldState;
	}
	
	
	
}
