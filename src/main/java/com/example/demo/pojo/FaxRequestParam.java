package com.example.demo.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author Administrator
 * @create 2021/10/20 0020 13:51
 */
@Data
public class FaxRequestParam {
    private String action;
    @JSONField(name = "access_id")
    private Integer accessId;
    @JSONField(name = "access_pwd")
    private String accessPwd;
    private Long sCallerID;
    private String sSenderEmail;
    private String sFaxType;
    private String sToFaxNumber;
    private String sResponseFormat;
    private String sAccountCode;
    private Integer sRetries;
    private String sCoverPage;
    private String sFaxFromHeader;
    private String sCPFromName;
    private String sCPToName;
    private String sCPOrganization;
    private String sCPSubject;
    private String sCPComments;
    private String sNotifyURL;
    @JSONField(name = "sFileName_1")
    private String sFileName;
    @JSONField(name = "sFileContent_1")
    private String sFileContent;

    private Integer sFaxDetailsID;
}
