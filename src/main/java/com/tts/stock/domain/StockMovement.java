package com.tts.stock.domain;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "stockmovement")
public class StockMovement {
    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int stockmovementId;
	private int itemId;
	private int userAccountId;
	private String movementType;
    private Integer fromDepartmentId;
    private int toDepartmentId;
	private int qty;
	private String remark;
    private Date movementDate;
    private Date created_at;
	private Date updated_at;

    public StockMovement() {
		super();
	}

    public int getStockmovementId() {
        return stockmovementId;
    }

    public void setStockmovementId(int stockmovementId) {
        this.stockmovementId = stockmovementId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(int userAccountId) {
        this.userAccountId = userAccountId;
    }

    public String getMovementType() {
        return movementType;
    }

    public void setMovementType(String movementType) {
        this.movementType = movementType;
    }

    public Integer getFromDepartmentId() {
        return fromDepartmentId;
    }

    public void setFromDepartmentId(Integer fromDepartmentId) {
        this.fromDepartmentId = fromDepartmentId;
    }

    public int getToDepartmentId() {
        return toDepartmentId;
    }

    public void setToDepartmentId(int toDepartmentId) {
        this.toDepartmentId = toDepartmentId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getMovementDate() {
        return movementDate;
    }

    public void setMovementDate(Date movementDate) {
        this.movementDate = movementDate;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    
}
