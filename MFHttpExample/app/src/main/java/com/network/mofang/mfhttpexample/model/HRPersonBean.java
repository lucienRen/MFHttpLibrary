package com.network.mofang.mfhttpexample.model;

import java.util.List;

/**
 * Created by mofang on 2016/9/23.
 */

public class HRPersonBean {
    /**
     * total : 2
     * items : [{"jobId":895,"maxTargetSalary":15,"location":"北京-东城","careerEducationSketch":"工作经历未填写","targetWorklocationSketch":"北京","uniqueKey":"c8a9f5634838dcdaf37038b4c85ced2c","id":11118,"minTargetSalary":10,"jobCategoryName":"Node.js","resumeId":515,"degree":"本科","workingExp":"在读学生","age":31,"avatarUrl":"http://7xio5n.com1.z0.glb.clouddn.com/mfhr_avatar_B50DCC64-94A6-4FEA-9159-1D7FE40199B0.jpg","name":"Hhhh","gender":"男"},{"careerEducationState":"现任","jobId":895,"maxTargetSalary":15,"location":"北京-东城","careerEducationSketch":"测试工程师@ibm","targetWorklocationSketch":"上海","uniqueKey":"ffe5f1becea72c1964e1f208a4ebbf50","id":11117,"minTargetSalary":10,"jobCategoryName":"Node.js","resumeId":85,"degree":"本科","workingExp":"在读学生","age":30,"avatarUrl":"http://7xio5n.com1.z0.glb.clouddn.com/AppCHeadPortrait7561b9d3d6f8de20e591e43e3d446ea6264d9c6a20160829150348485.png","name":"测试","gender":"男"}]
     */

    private int total;
    /**
     * jobId : 895
     * maxTargetSalary : 15
     * location : 北京-东城
     * careerEducationSketch : 工作经历未填写
     * targetWorklocationSketch : 北京
     * uniqueKey : c8a9f5634838dcdaf37038b4c85ced2c
     * id : 11118
     * minTargetSalary : 10
     * jobCategoryName : Node.js
     * resumeId : 515
     * degree : 本科
     * workingExp : 在读学生
     * age : 31
     * avatarUrl : http://7xio5n.com1.z0.glb.clouddn.com/mfhr_avatar_B50DCC64-94A6-4FEA-9159-1D7FE40199B0.jpg
     * name : Hhhh
     * gender : 男
     */

    private List<PersonBean> items;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<PersonBean> getItems() {
        return items;
    }

    public void setItems(List<PersonBean> items) {
        this.items = items;
    }

    public static class PersonBean {
        private int jobId;
        private int maxTargetSalary;
        private String location;
        private String careerEducationSketch;
        private String targetWorklocationSketch;
        private String targetWorkLocation;
        private String careerEducationState;
        private String uniqueKey;
        private int id;
        private int minTargetSalary;
        private String jobCategoryName;
        private String targetJobTypesShow;
        private int resumeId;
        private String degree;
        private String workingExp;
        private int age;
        private String avatarUrl;
        private String name;
        private String gender;

        public int getJobId() {
            return jobId;
        }

        public String getCareerEducationState() {
            return careerEducationState;
        }

        public String getTargetWorkLocation() {
            return targetWorkLocation;
        }

        public void setTargetWorkLocation(String targetWorkLocation) {
            this.targetWorkLocation = targetWorkLocation;
        }

        public void setCareerEducationState(String careerEducationState) {
            this.careerEducationState = careerEducationState;
        }

        public void setTargetJobTypesShow(String targetJobTypesShow) {
            this.targetJobTypesShow = targetJobTypesShow;
        }

        public String getTargetJobTypesShow() {
            return targetJobTypesShow;
        }

        public void setJobId(int jobId) {
            this.jobId = jobId;
        }

        public int getMaxTargetSalary() {
            return maxTargetSalary;
        }

        public void setMaxTargetSalary(int maxTargetSalary) {
            this.maxTargetSalary = maxTargetSalary;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getCareerEducationSketch() {
            return careerEducationSketch;
        }

        public void setCareerEducationSketch(String careerEducationSketch) {
            this.careerEducationSketch = careerEducationSketch;
        }

        public String getTargetWorklocationSketch() {
            return targetWorklocationSketch;
        }

        public void setTargetWorklocationSketch(String targetWorklocationSketch) {
            this.targetWorklocationSketch = targetWorklocationSketch;
        }

        public String getUniqueKey() {
            return uniqueKey;
        }

        public void setUniqueKey(String uniqueKey) {
            this.uniqueKey = uniqueKey;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getMinTargetSalary() {
            return minTargetSalary;
        }

        public void setMinTargetSalary(int minTargetSalary) {
            this.minTargetSalary = minTargetSalary;
        }

        public String getJobCategoryName() {
            return jobCategoryName;
        }

        public void setJobCategoryName(String jobCategoryName) {
            this.jobCategoryName = jobCategoryName;
        }

        public int getResumeId() {
            return resumeId;
        }

        public void setResumeId(int resumeId) {
            this.resumeId = resumeId;
        }

        public String getDegree() {
            return degree;
        }

        public void setDegree(String degree) {
            this.degree = degree;
        }

        public String getWorkingExp() {
            return workingExp;
        }

        public void setWorkingExp(String workingExp) {
            this.workingExp = workingExp;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getAvatarUrl() {
            return avatarUrl;
        }

        public void setAvatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }
    }
}
