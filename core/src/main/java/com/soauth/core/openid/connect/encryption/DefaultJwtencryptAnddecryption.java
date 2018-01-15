package com.soauth.core.openid.connect.encryption;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.jose4j.jwe.ContentEncryptionAlgorithmIdentifiers;
import org.jose4j.jwe.JsonWebEncryption;
import org.jose4j.jwe.KeyManagementAlgorithmIdentifiers;
import org.jose4j.jwk.*;
import org.jose4j.keys.EllipticCurves;
import org.jose4j.lang.JoseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.security.PrivateKey;
import java.security.PublicKey;


/**
 *
 * @author zhoujie
 * @date 2017/11/14
 *
 * Ĭ�ϵ�jweʵ��
 * �ǶԳ�rsa
 * ����ʹ��jose4j���ɵĹ�Կ/˽Կ,Ҳ����ʹ�ô�classPath�м��ص�pulibc key / private key(�����Լ�ʹ��Openssl���� 2048bit X509�Ĺ�Կ/˽Կ)
 */

public class DefaultJwtencryptAnddecryption {

    private static final Logger log = LoggerFactory.getLogger(DefaultJwtencryptAnddecryption.class);

    public static  final  String DEFAULT_ENCRYPTION_ALG= ContentEncryptionAlgorithmIdentifiers.AES_128_CBC_HMAC_SHA_256;

    private static RsaJsonWebKey jwk=null;

    public RsaJsonWebKey getKey() {

        if(jwk==null) {
                     try {
                         jwk = RsaJwkGenerator.generateJwk(2048);
                         log.debug("return new Json Web Key");
                         return jwk;

                     } catch (JoseException e) {
                         e.printStackTrace();
                     }
        }

        return jwk;

    }

    public String encryptJwtAsrsa256(final JsonWebKey jwk, final  String payload) throws JoseException {
        log.debug("���� jwt");
        final JsonWebEncryption jwe= new JsonWebEncryption();
        //alg
        jwe.setAlgorithmHeaderValue(KeyManagementAlgorithmIdentifiers.RSA_OAEP_256);
        //enc
        jwe.setEncryptionMethodHeaderParameter(DEFAULT_ENCRYPTION_ALG);

       String kid = StringUtils.isEmpty(jwk.getKeyId()) ? RandomStringUtils.random(6) : jwk.getKeyId();

            // kid
        jwe.setKeyIdHeaderValue(kid);
             //key
        jwe.setKey(jwk.getKey());

        jwe.setContentTypeHeaderValue("jwt");
        //encry idtoken
        jwe.setPayload(payload);

        String encrtyIdtoken=null;

           // base64
         encrtyIdtoken =jwe.getCompactSerialization();

        return encrtyIdtoken;
    }

    public String  decryptJwt(final PrivateKey jwk, String payload) throws JoseException {
        final JsonWebEncryption jwe= new JsonWebEncryption();

            jwe.setKey(jwk);
            jwe.setCompactSerialization(payload);
            log.debug("���ܳɹ�");
            return jwe.getPayload();
    }



}
