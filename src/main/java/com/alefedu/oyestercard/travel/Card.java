package com.alefedu.oyestercard.travel;


public class Card {
	
	private String name;
	private Double balance;
	private String zone;
	private Direction action;
	
	
	public Card(String name, Double balance) {
		if(balance < 0.0D) {
			throw new IllegalArgumentException("Card should not be loaded with Negative Amount at the Biginning");
		}else {
			this.name = name;
			this.balance = balance;
			this.zone = "";
			this.action = Direction.UNKNOWN;
		}
	}
	
	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public Direction getAction() {
		return action;
	}

	public void setAction(Direction action) {
		this.action = action;
	}

	public void addBalance(Double balance) {
		this.balance = this.balance+(balance);
		System.out.println("Current balance in the card : $" + this.balance + " After the Addition/deletion of Journey Amount :$" + balance);
		if(this.balance < 0.0D) {
			throw new IllegalArgumentException("Card should be renew to use for Next Journeys");
		}
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	
	

	@Override
	public String toString() {
		return "Card [name=" + name + ", balance=" + balance + ", zone=" + zone + ", action=" + action + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((action == null) ? 0 : action.hashCode());
		result = prime * result + ((balance == null) ? 0 : balance.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((zone == null) ? 0 : zone.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (action != other.action)
			return false;
		if (balance == null) {
			if (other.balance != null)
				return false;
		} else if (!balance.equals(other.balance))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (zone == null) {
			if (other.zone != null)
				return false;
		} else if (!zone.equals(other.zone))
			return false;
		return true;
	}

	
}
