package com.tts.stock.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tts.stock.dto.ItemDepartmentDto;

@Entity
@Table(name = "itemdepartment")
public class ItemDepartment {
    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private int itemDepartmentId;
	private int itemId;
	private int departmentId;

    public ItemDepartment() {
        super();
    }

    public ItemDepartment(ItemDepartmentDto itemDepartmentDto) {
        //TODO Auto-generated constructor stub
        this.itemDepartmentId = itemDepartmentDto.getItemDepartmentId();
        this.itemId = itemDepartmentDto.getItemId();
        this.departmentId = itemDepartmentDto.getDepartmentId();
    }

    public int getItemDepartmentId() {
        return itemDepartmentId;
    }

    public void setItemDepartmentId(int itemDepartmentId) {
        this.itemDepartmentId = itemDepartmentId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    

}
