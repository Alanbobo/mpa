package com.commandcenter.common.activemq.handler;

import com.commandcenter.common.activemq.handler.bean.*;
import org.apache.log4j.Logger;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author r25437
 * @create 2018-09-06 11:39
 * @desc 消息队列发送
 **/
public class CoreHandler {
    private CountDownLatch latch;
    private MesParameter mesParameter;
    private String guid;
    private static Logger log = Logger.getLogger(CoreHandler.class);

    private CoreHandler(MesParameter mesParameter) {
        this.mesParameter = mesParameter;
        initHandler();
    }

    private void initHandler() {
        this.guid = UUID.randomUUID().toString();
        latch = new CountDownLatch(1);
    }

    /**
     * 将消息参数封装成要发送的消息体
     *
     * @return
     */
    private Message packMes(boolean responsed) {
        Message message = new Message();
        MesHeader mesHeader = new MesHeader();
        mesHeader.setType(MesHeader.TYPE_REQUEST);
        mesHeader.setMethod(MesHeader.METHOD_CALL);
        if (responsed) {
            mesHeader.setResp(MesHeader.RESP_YES);
        } else {
            mesHeader.setResp(MesHeader.RESP_NO);
        }
        mesHeader.setDestination(mesParameter.getDestination());
        mesHeader.setUuid(guid);
        message.setMesHeader(mesHeader);
        message.setMesBody(mesParameter.getMesBody());

        return message;
    }

    /**
     * 将闭锁存入map中,然后开始等待响应,响应超时为3秒
     *
     * @return
     */
    private Boolean waitResp() {
        Boolean result = false;
        Mapping.getSafeMapping().put(guid, MappingBean.getInstance(latch));
        try {
            result = latch.await(Integer.parseInt("1"), TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            log.warn("CoreHandler线程: <" + guid + ">线程等待中被中断");
            e.printStackTrace();
        }
        return result;
    }

    private String getResult(Boolean flag) {
        String result = null;
        if (flag == true) {
            result = Mapping.getSafeMapping().get(guid).getResponse();
            log.info("CoreHandler线程: <" + guid + ">成功收到返回值:" + result);
        }
        Mapping.getSafeMapping().remove(guid);
        return result;
    }


    public static String call(MesParameter mesParameter) {
        //参数检查
        boolean checkFlag = MesParameter.checkMesParameter(mesParameter);
        if (!checkFlag) {
            return null;
        }

        CoreHandler coreHandler = new CoreHandler(mesParameter);
        //打包消息
        Message message = coreHandler.packMes(true);
        //发送消息
//        MessageWriter.writerHandler(message);
        //等待响应
        Boolean successFlag = coreHandler.waitResp();
        //获取并返回结果
        String result = coreHandler.getResult(successFlag);

        return result;
    }

    public static String call(MesParameter mesParameter, boolean responsed) {
        //参数检查
        boolean checkFlag = MesParameter.checkMesParameter(mesParameter);
        if (!checkFlag) {
            return null;
        }

        CoreHandler coreHandler = new CoreHandler(mesParameter);
        //打包消息
        Message message = coreHandler.packMes(responsed);
        //发送消息
//        MessageWriter.writerHandler(message);
        if (responsed) {
            //等待响应
            Boolean successFlag = coreHandler.waitResp();
            //获取并返回结果
            String result = coreHandler.getResult(successFlag);

            return result;
        }
        return null;
    }
}
