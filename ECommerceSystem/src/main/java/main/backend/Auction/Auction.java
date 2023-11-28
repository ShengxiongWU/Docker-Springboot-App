package main.backend.Auction;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import main.database.user;
import main.database.product;

@Entity
public class Auction {
	
    @Id
    @GeneratedValue
    private int Auction_id;
    
    @Column(name = "create_time")
    private LocalDateTime createTime;
    
    @Column(name = "start_time")
    private LocalDateTime startTime;
    
    @Column(name = "end_time")
    private LocalDateTime endTime;
    
    @ManyToOne
    @JoinColumn(name = "seller_id")
    private user seller;

	@ManyToMany
	@JoinTable(
			name = "auction_product",
			joinColumns = @JoinColumn(name = "auction_id"),
			inverseJoinColumns = @JoinColumn(name = "product_id")
	)
    private Set<product> product;
    
    @ManyToMany
    @JoinTable(
        name = "auction_participants",
        joinColumns = @JoinColumn(name = "auction_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<user> participant_list;
    
    @Column(name = "starting_price")
    private int startingPrice;
    
    @Column(name = "commission_fee")
    private int commissionFee;
    
    @Column(name = "minimum_bid_increment")
    private int minimum_bid_increment;
    
    @Column(name = "auction_type")
    private String auction_type;
    
    @Column(name = "current_highest_price")
    private int currentHighestPrice;
    
    @ManyToOne
    @JoinColumn(name = "current_highest_user_id")
    private user currentHighestUser;
    
    @Column(name = "state")
    private String state;
	
	public Auction() {
		this.createTime = LocalDateTime.now();
		this.product = new TreeSet<>();
		this.participant_list = new TreeSet<>();
		this.state = "No Start";
	}
	
	
	
	private Auction(LocalDateTime startTime, LocalDateTime endTime, user seller,
			int startingPrice, int commissionFee, int minimum_bid_increment,String auction_type) {
		super();
		this.createTime = LocalDateTime.now();
		this.startTime = startTime;
		this.endTime = endTime;
		this.seller = seller;
		this.product = new TreeSet<>();
		this.participant_list = new TreeSet<>();
		this.startingPrice = startingPrice;
		this.commissionFee = commissionFee;
		this.minimum_bid_increment = minimum_bid_increment;
		this.auction_type = auction_type;
		this.state = "No Start";
	}


	public static Auction createAuction() {
		return new Auction();
	}
	
	public static Auction createAuction(LocalDateTime startTime, LocalDateTime endTime, user seller, main.database.product product,
			int startingPrice, int commissionFee, int minimum_bid_increment, String auction_type) {
		return new Auction(startTime, endTime, seller,startingPrice,
				commissionFee, minimum_bid_increment, auction_type);
	}
	
	public boolean checkIfStart() {
		if(startTime==null||(LocalDateTime.now().compareTo(startTime))<0){
			return false;
		}else {
			if(this.state.equals("No Start")) {
				startAuction();
			}
			return true;
		}
	}
	
	public void startAuction() {
		this.state = "Started";
	}
	
	public boolean checkIfEnd() {
		if(endTime==null||(LocalDateTime.now().compareTo(endTime))<0){
			return false;
		}else {
			endAuction();
			return true;
		}
	}
	
	public void endAuction() {
		this.state = "Ended";
	}
	
	public boolean attendAuction(user u) {
		for(user i: this.participant_list) {
			if(i.getUserId().equals(u.getUserId())) {
				return false;
			}
		}
		this.participant_list.add(u);
		return true;
	}
	
	public boolean bid(user u, int price) {
		if(price>=(Math.max(startingPrice, currentHighestPrice)+this.minimum_bid_increment)&&(LocalDateTime.now().compareTo(endTime))<0) {
			this.currentHighestPrice = price;
			this.currentHighestUser = u;
			return true;
		}else {
			return false;
		}
	}



	public int getAuction_id() {
		return Auction_id;
	}



	public void setAuction_id(int auction_id) {
		Auction_id = auction_id;
	}



	public LocalDateTime getCreateTime() {
		return createTime;
	}



	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}



	public LocalDateTime getStartTime() {
		return startTime;
	}



	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}



	public LocalDateTime getEndTime() {
		return endTime;
	}



	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}



	public user getSeller() {
		return seller;
	}



	public void setSeller(user seller) {
		this.seller = seller;
	}



	public Set<product> getProduct() {
		return product;
	}



	public void setProduct(Set<product> product) {
		this.product = product;
	}



	public Set<user> getParticipant_list() {
		return participant_list;
	}



	public void setParticipant_list(Set<user> participant_list) {
		this.participant_list = participant_list;
	}



	public int getStartingPrice() {
		return startingPrice;
	}



	public void setStartingPrice(int startingPrice) {
		this.startingPrice = startingPrice;
	}



	public int getCommissionFee() {
		return commissionFee;
	}



	public void setCommissionFee(int commissionFee) {
		this.commissionFee = commissionFee;
	}



	public int getMinimum_bid_increment() {
		return minimum_bid_increment;
	}



	public void setMinimum_bid_increment(int minimum_bid_increment) {
		this.minimum_bid_increment = minimum_bid_increment;
	}



	public String getAuction_type() {
		return auction_type;
	}



	public void setAuction_type(String auction_type) {
		this.auction_type = auction_type;
	}



	public int getCurrentHighestPrice() {
		return currentHighestPrice;
	}



	public void setCurrentHighestPrice(int currentHighestPrice) {
		this.currentHighestPrice = currentHighestPrice;
	}



	public user getCurrentHighestUser() {
		return currentHighestUser;
	}



	public void setCurrentHighestUser(user currentHighestUser) {
		this.currentHighestUser = currentHighestUser;
	}



	public String getState() {
		return state;
	}



	public void setState(String state) {
		this.state = state;
	}
	
	
	
	
}
