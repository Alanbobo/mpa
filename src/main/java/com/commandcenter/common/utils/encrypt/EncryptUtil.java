package com.commandcenter.common.utils.encrypt;

import com.commandcenter.common.utils.Constant;
import com.commandcenter.common.utils.R;

import static com.commandcenter.common.utils.encrypt.AesEncryptUtil.aesEncrypt;

/**
 * @author r25437
 * @create 2018-09-11 18:56
 * @desc 加密工具类
 **/
public class EncryptUtil {
    public static R encryptInput(String json){
        R r = new R();
        try {
            byte[] cipherData = RSAEncrypt.encrypt(RSAEncrypt.loadPrivateKeyByStr(RSAEncrypt.loadPrivateKeyByFile(Constant.getKeyPath())), Constant.ENCRYPT_SALT.getBytes());
            String encryptSalt = Base64.encode(cipherData);
            //生成签名 本次不使用签名
//			String signstr= RSASignature.sign(Constant.SIGN,RSAEncrypt.loadSignPublicKeyByFile(Constant.getKeyPath()));
            //加密json串
            String encryptJson = aesEncrypt(json, Constant.ENCRYPT_SALT + Constant.ENCRYPT_KEY);
            r = new R();
            r.put("salt", encryptSalt);
            r.put("param", encryptJson);
            return r;
        }catch (Exception e){
            e.printStackTrace();
            return r;
        }
    }
}
