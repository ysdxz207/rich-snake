/**
 * 
 */
package com.puyixiaowo.rsnake.enums;

/**
 * @author weishaoqiang
 * @date 2016年12月25日 下午5:06:20
 */
public enum ImagesPath {
	MENU_ITEM_SELECTED("selected.png", "菜单项选中样式");
	
	
	ImagesPath(String fileName, String description){
		this.setFileName(fileName);
		this.setDescription(description);
	}
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	private String fileName;
	private String description;
}
