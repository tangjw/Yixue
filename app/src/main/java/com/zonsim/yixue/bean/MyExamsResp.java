package com.zonsim.yixue.bean;

import java.util.List;

public class MyExamsResp {
    
    private int ret;
    private String msg;
    
    private List<ExamBean> info;
    
    public int getRet() {
        return ret;
    }
    
    public void setRet(int ret) {
        this.ret = ret;
    }
    
    public String getMsg() {
        return msg;
    }
    
    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    public List<ExamBean> getInfo() {
        return info;
    }
    
    public void setInfo(List<ExamBean> info) {
        this.info = info;
    }
    
    public static class ExamBean {
        private String examDate;
        private String order_number;
        private String fee;
        private int examType;
        private boolean applyQualify;
        private int applyProfessionLevel;
        private int manner;
        private String type;
        private String professionName;
        private Object userId;
        private int examineeId;
        private boolean makeUpExam;
        private int applyProfessionId;
        private int id;
        private String applyDate;
        private String applyStatus;
        private String username;
        private String status;
        private String ticketNumber;
        
        public String getProfessionName() {
            return professionName;
        }
        
        public void setProfessionName(String professionName) {
            this.professionName = professionName;
        }
        
        public String getTicketNumber() {
            return ticketNumber;
        }
        
        public void setTicketNumber(String ticketNumber) {
            this.ticketNumber = ticketNumber;
        }
        
        public String getExamDate() {
            return examDate;
        }
        
        public void setExamDate(String examDate) {
            this.examDate = examDate;
        }
        
        public String getOrder_number() {
            return order_number;
        }
        
        public void setOrder_number(String order_number) {
            this.order_number = order_number;
        }
        
        public String getFee() {
            return fee;
        }
        
        public void setFee(String fee) {
            this.fee = fee;
        }
        
        public int getExamType() {
            return examType;
        }
        
        public void setExamType(int examType) {
            this.examType = examType;
        }
        
        public boolean isApplyQualify() {
            return applyQualify;
        }
        
        public void setApplyQualify(boolean applyQualify) {
            this.applyQualify = applyQualify;
        }
        
        public int getApplyProfessionLevel() {
            return applyProfessionLevel;
        }
        
        public void setApplyProfessionLevel(int applyProfessionLevel) {
            this.applyProfessionLevel = applyProfessionLevel;
        }
        
        public int getManner() {
            return manner;
        }
        
        public void setManner(int manner) {
            this.manner = manner;
        }
        
        public String getType() {
            return type;
        }
        
        public void setType(String type) {
            this.type = type;
        }
        
        public Object getUserId() {
            return userId;
        }
        
        public void setUserId(Object userId) {
            this.userId = userId;
        }
        
        public int getExamineeId() {
            return examineeId;
        }
        
        public void setExamineeId(int examineeId) {
            this.examineeId = examineeId;
        }
        
        public boolean isMakeUpExam() {
            return makeUpExam;
        }
        
        public void setMakeUpExam(boolean makeUpExam) {
            this.makeUpExam = makeUpExam;
        }
        
        public int getApplyProfessionId() {
            return applyProfessionId;
        }
        
        public void setApplyProfessionId(int applyProfessionId) {
            this.applyProfessionId = applyProfessionId;
        }
        
        public int getId() {
            return id;
        }
        
        public void setId(int id) {
            this.id = id;
        }
        
        public String getApplyDate() {
            return applyDate;
        }
        
        public void setApplyDate(String applyDate) {
            this.applyDate = applyDate;
        }
        
        public String getApplyStatus() {
            return applyStatus;
        }
        
        public void setApplyStatus(String applyStatus) {
            this.applyStatus = applyStatus;
        }
        
        public String getUsername() {
            return username;
        }
        
        public void setUsername(String username) {
            this.username = username;
        }
        
        public String getStatus() {
            return status;
        }
        
        public void setStatus(String status) {
            this.status = status;
        }
    }
}
