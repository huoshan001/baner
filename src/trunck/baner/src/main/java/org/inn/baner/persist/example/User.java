package org.inn.baner.persist.example;

public class User {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.mobileno
     *
     * @mbg.generated
     */
    private String mobileno;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.nickname
     *
     * @mbg.generated
     */
    private String nickname;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.passwd
     *
     * @mbg.generated
     */
    private String passwd;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.mobileno
     *
     * @return the value of user.mobileno
     *
     * @mbg.generated
     */
    public String getMobileno() {
        return mobileno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.mobileno
     *
     * @param mobileno the value for user.mobileno
     *
     * @mbg.generated
     */
    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.nickname
     *
     * @return the value of user.nickname
     *
     * @mbg.generated
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.nickname
     *
     * @param nickname the value for user.nickname
     *
     * @mbg.generated
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.passwd
     *
     * @return the value of user.passwd
     *
     * @mbg.generated
     */
    public String getPasswd() {
        return passwd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.passwd
     *
     * @param passwd the value for user.passwd
     *
     * @mbg.generated
     */
    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}