package com.enchere.appli.model;

import com.enchere.appli.dao.AdminDAO;
import com.enchere.appli.dao.TokenDAO;

public class Admin {

    private int id;
    private String userName;
    private String passWord;

    /**
     * @return int return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return String return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return String return the passWord
     */
    public String getPassWord() {
        return passWord;
    }

    /**
     * @param passWord the passWord to set
     */
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Admin() {
    }

    public Admin(int id, String userName, String passWord) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
    }

    public Admin(String userName, String passWord) {

        this.userName = userName;
        this.passWord = passWord;
    }

    /*public Admin runLogin() throws Exception {
        Admin result = new Admin();
        result.setUserName(this.getUserName());
        result.setPassWord(this.getPassWord());
        result = AdminDAO.toLog(result);
        TokenDAO.insertToken(result);
        return result;
    }*/
}
