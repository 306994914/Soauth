package com.soauth.server.oauth.validator.authzgrant;

import com.soauth.server.oauth.SoauthAuthRequest;
import com.soauth.server.oauth.message.BaseAccessTokenHandler;
import com.soauth.server.oauth.message.BaseSoauthResponse;
import com.soauth.server.oauth.validator.client.BaseClientValidator;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 *
 * @author zhoujie
 * @date 2017/9/22
 * ���䲢����֤ authorize���� code token��
 */
public  abstract class BaseAuthzHandlerValidator extends BaseAccessTokenHandler {
     private static final Logger log = LoggerFactory.getLogger(BaseAuthzHandlerValidator.class);

     protected SoauthAuthRequest soauthAuthRequest;
     protected HttpServletResponse response;

     protected final String issuer="http://localhost:8000/SoauthServer";
    /**
     * ��׼�û�ͨ��Ч��
     */
    protected boolean userApproved = false;

    /**
     * ����û��Ƿ��¼��ʶ
     */
    protected  boolean userLogin=false;

     public BaseAuthzHandlerValidator(SoauthAuthRequest soauthAuthRequest, HttpServletResponse response){
          this.response=response;
          this.soauthAuthRequest=soauthAuthRequest;
     }

    /**
     *  ���ฺ��ʵ������ǰҵ�����֤��ʵ��
     * @return example: return new CodeClientValidator
     */
    protected  abstract BaseClientValidator getValidator();

    public void  verify() throws OAuthSystemException, IOException {

           if(checkClientValidate()){
               return;
           }
          log.info("check login....");
           if (!checklogin()){
               return;
           }
        log.info("check Approval....");
           if (checkApproval()){
               return;
           }

           callbackResponse();
      }


    /**
     * ����û��Ƿ��¼
     * @return
     */
    protected boolean checklogin(){
          return  userLogin=true;
      }
    /**
     *  ���ͻ��˲���
     * @return
     */
    protected boolean checkClientValidate() throws OAuthSystemException {
          BaseClientValidator validator= getValidator();
          log.info("checkClientValidator={}, client id={}",validator,soauthAuthRequest.getClientId());
          final OAuthResponse response=validator.validate();

          if(response ==null){
              return  false;
          }
          else{
              BaseSoauthResponse.SuccessResponse.oauthjsonResponse(this.response,response,false);
              return  true;
          }

      }


    /**
     * Approval���
     * @return
     * @throws IOException
     * @throws OAuthSystemException
     */
    protected boolean checkApproval() throws IOException, OAuthSystemException {
         /* if (isPost() && !getclientDetails().isTrusted()) {
            //submit approval
            final HttpServletRequest request = soauthAuthRequest.request();
            final String oauthApproval = request.getParameter("user_oauth_approval");
            final String checkApproval="true";

            if (!checkApproval.equalsIgnoreCase(oauthApproval)) {
                //Deny action
                invalidApprovalDeny(soauthAuthRequest, response);
                return true;
            } else {
                userApproved = true;
                return false;
            }

        }*/
        return false;
    }

    @Override
    protected  String getClientid(){
     return soauthAuthRequest.getClientId();
    }

    /**
     * ������ݵ�ǰҵ��Կͻ�������������Ӧ.
     * @throws OAuthSystemException
     * @throws IOException
     */
    protected abstract void callbackResponse() throws OAuthSystemException, IOException;

    protected boolean isPost() {
        return RequestMethod.POST.name().equalsIgnoreCase(soauthAuthRequest.request().getMethod());
    }
}
