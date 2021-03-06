/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alipay.sofa.registry.server.session.bootstrap;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * The type Session server config bean.
 * @author shangyu.wh
 * @version $Id : SessionServerConfigBean.java, v 0.1 2017-11-14 11:49 synex Exp $
 */
@ConfigurationProperties(prefix = SessionServerConfigBean.PREFIX)
public class SessionServerConfigBean implements SessionServerConfig {

    /**
     * The constant PREFIX.
     */
    public static final String PREFIX                                  = "session.server";

    private int                serverPort                              = 9600;

    private int                metaServerPort                          = 9610;

    private int                dataServerPort                          = 9620;

    private int                httpServerPort;

    private int                schedulerHeartbeatTimeout               = 3;

    private int                schedulerHeartbeatFirstDelay            = 3;

    private int                schedulerHeartbeatExpBackOffBound       = 10;

    private int                schedulerGetSessionNodeTimeout          = 3;

    private int                schedulerGetSessionNodeFirstDelay       = 5;

    private int                schedulerGetSessionNodeExpBackOffBound  = 10;

    private int                schedulerFetchDataTimeout               = 1;               //MINUTES

    private int                schedulerFetchDataFirstDelay            = 30;

    private int                schedulerFetchDataExpBackOffBound       = 10;

    private int                schedulerConnectMetaTimeout             = 5;

    private int                schedulerConnectMetaFirstDelay          = 5;

    private int                schedulerConnectMetaExpBackOffBound     = 10;

    private int                schedulerConnectDataTimeout             = 3;

    private int                schedulerConnectDataFirstDelay          = 3;

    private int                schedulerConnectDataExpBackOffBound     = 10;

    private int                cancelDataTaskRetryTimes                = 5;

    private int                cancelDataTaskRetryFirstDelay           = 100;

    private long               cancelDataTaskRetryIncrementDelay       = 200;

    private int                dataChangeFetchTaskRetryTimes           = 3;

    private int                subscriberRegisterFetchRetryTimes       = 3;

    private int                receivedDataMultiPushTaskRetryTimes     = 3;

    private int                sessionRegisterDataServerTaskRetryTimes = 5;

    private int                defaultSessionExecutorMinPoolSize       = cpus();

    private int                defaultSessionExecutorMaxPoolSize       = cpus() * 5;      //5*CPUs by default

    private long               defaultSessionExecutorKeepAliveTime     = 60;

    private int                accessDataExecutorMinPoolSize           = 100;

    private int                accessDataExecutorMaxPoolSize           = 400;

    private int                accessDataExecutorQueueSize             = 10000;

    private long               accessDataExecutorKeepAliveTime         = 60;

    private int                pushTaskExecutorMinPoolSize             = 40;

    private int                pushTaskExecutorMaxPoolSize             = 400;

    private int                pushTaskExecutorQueueSize               = 100000;

    private long               pushTaskExecutorKeepAliveTime           = 60;

    private int                dataChangeExecutorMinPoolSize           = 40;

    private int                dataChangeExecutorMaxPoolSize           = 400;

    private int                dataChangeExecutorQueueSize             = 100000;

    private long               dataChangeExecutorKeepAliveTime         = 60;

    private int                disconnectClientExecutorMinPoolSize     = 40;

    private int                disconnectClientExecutorMaxPoolSize     = 200;

    private int                disconnectClientExecutorQueueSize       = 10000;

    private int                dataChangeFetchTaskMaxBufferSize        = 1000000;

    private int                dataChangeFetchTaskWorkerSize           = 100;

    private int                clientNodeExchangeTimeOut               = 1000;            //time out cause netty HashedWheelTimer occupy a lot of mem

    private int                dataNodeExchangeTimeOut                 = 3000;

    private int                metaNodeExchangeTimeOut                 = 3000;

    private int                numberOfReplicas                        = 1000;

    private int                userDataPushRetryWheelTicksSize         = 5120;

    private int                userDataPushRetryWheelTicksDuration     = 100;

    private int                pushDataTaskRetryFirstDelay             = 500;

    private long               pushDataTaskRetryIncrementDelay         = 500;

    private String             sessionServerRegion;

    private String             sessionServerDataCenter;

    private boolean            stopPushSwitch                          = false;

    private boolean            beginDataFetchTask                      = false;

    //begin config for enterprise version

    /** forever close push zone，such as:RZBETA */
    private String             invalidForeverZones                     = "";
    /** config regex,exception to the rule of forever close push zone*/
    private String             invalidIgnoreDataidRegex                = "";

    private Set<String>        invalidForeverZonesSet;

    private Pattern            invalidIgnoreDataIdPattern              = null;

    private String             pushEmptyDataDataIdPrefixes             = "";

    private Set<String>        pushEmptyDataDataIdPrefixesSet;

    //end config for enterprise version

    private CommonConfig       commonConfig;

    /**
     * constructor
     * @param commonConfig
     */
    public SessionServerConfigBean(CommonConfig commonConfig) {
        this.commonConfig = commonConfig;
    }

    /**
     * Getter method for property <tt>serverPort</tt>.
     *
     * @return property value of serverPort
     */
    @Override
    public int getServerPort() {
        return serverPort;
    }

    /**
     * Setter method for property <tt>serverPort</tt>.
     *
     * @param serverPort value to be assigned to property serverPort
     */
    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    /**
     * Getter method for property <tt>schedulerHeartbeatTimeout</tt>.
     *
     * @return property value of schedulerHeartbeatTimeout
     */
    @Override
    public int getSchedulerHeartbeatTimeout() {
        return schedulerHeartbeatTimeout;
    }

    /**
     * Setter method for property <tt>schedulerHeartbeatTimeout</tt>.
     *
     * @param schedulerHeartbeatTimeout value to be assigned to property schedulerHeartbeatTimeout
     */
    public void setSchedulerHeartbeatTimeout(int schedulerHeartbeatTimeout) {
        this.schedulerHeartbeatTimeout = schedulerHeartbeatTimeout;
    }

    /**
     * Getter method for property <tt>schedulerHeartbeatFirstDelay</tt>.
     *
     * @return property value of schedulerHeartbeatFirstDelay
     */
    @Override
    public int getSchedulerHeartbeatFirstDelay() {
        return schedulerHeartbeatFirstDelay;
    }

    /**
     * Setter method for property <tt>schedulerHeartbeatFirstDelay</tt>.
     *
     * @param schedulerHeartbeatFirstDelay value to be assigned to property schedulerHeartbeatFirstDelay
     */
    public void setSchedulerHeartbeatFirstDelay(int schedulerHeartbeatFirstDelay) {
        this.schedulerHeartbeatFirstDelay = schedulerHeartbeatFirstDelay;
    }

    /**
     * Getter method for property <tt>schedulerHeartbeatExpBackOffBound</tt>.
     *
     * @return property value of schedulerHeartbeatExpBackOffBound
     */
    @Override
    public int getSchedulerHeartbeatExpBackOffBound() {
        return schedulerHeartbeatExpBackOffBound;
    }

    /**
     * Setter method for property <tt>schedulerHeartbeatExpBackOffBound</tt>.
     *
     * @param schedulerHeartbeatExpBackOffBound value to be assigned to property schedulerHeartbeatExpBackOffBound
     */
    public void setSchedulerHeartbeatExpBackOffBound(int schedulerHeartbeatExpBackOffBound) {
        this.schedulerHeartbeatExpBackOffBound = schedulerHeartbeatExpBackOffBound;
    }

    /**
     * Getter method for property <tt>schedulerFetchDataTimeout</tt>.
     *
     * @return property value of schedulerFetchDataTimeout
     */
    @Override
    public int getSchedulerFetchDataTimeout() {
        return schedulerFetchDataTimeout;
    }

    /**
     * Setter method for property <tt>schedulerFetchDataTimeout</tt>.
     *
     * @param schedulerFetchDataTimeout value to be assigned to property schedulerFetchDataTimeout
     */
    public void setSchedulerFetchDataTimeout(int schedulerFetchDataTimeout) {
        this.schedulerFetchDataTimeout = schedulerFetchDataTimeout;
    }

    /**
     * Getter method for property <tt>schedulerFetchDataFirstDelay</tt>.
     *
     * @return property value of schedulerFetchDataFirstDelay
     */
    @Override
    public int getSchedulerFetchDataFirstDelay() {
        return schedulerFetchDataFirstDelay;
    }

    /**
     * Setter method for property <tt>schedulerFetchDataFirstDelay</tt>.
     *
     * @param schedulerFetchDataFirstDelay value to be assigned to property schedulerFetchDataFirstDelay
     */
    public void setSchedulerFetchDataFirstDelay(int schedulerFetchDataFirstDelay) {
        this.schedulerFetchDataFirstDelay = schedulerFetchDataFirstDelay;
    }

    /**
     * Getter method for property <tt>schedulerFetchDataExpBackOffBound</tt>.
     *
     * @return property value of schedulerFetchDataExpBackOffBound
     */
    @Override
    public int getSchedulerFetchDataExpBackOffBound() {
        return schedulerFetchDataExpBackOffBound;
    }

    /**
     * Setter method for property <tt>schedulerFetchDataExpBackOffBound</tt>.
     *
     * @param schedulerFetchDataExpBackOffBound value to be assigned to property schedulerFetchDataExpBackOffBound
     */
    public void setSchedulerFetchDataExpBackOffBound(int schedulerFetchDataExpBackOffBound) {
        this.schedulerFetchDataExpBackOffBound = schedulerFetchDataExpBackOffBound;
    }

    /**
     * Getter method for property <tt>cancelDataTaskRetryTimes</tt>.
     *
     * @return property value of cancelDataTaskRetryTimes
     */
    @Override
    public int getCancelDataTaskRetryTimes() {
        return cancelDataTaskRetryTimes;
    }

    /**
     * Setter method for property <tt>cancelDataTaskRetryTimes</tt>.
     *
     * @param cancelDataTaskRetryTimes value to be assigned to property cancelDataTaskRetryTimes
     */
    public void setCancelDataTaskRetryTimes(int cancelDataTaskRetryTimes) {
        this.cancelDataTaskRetryTimes = cancelDataTaskRetryTimes;
    }

    /**
     * Getter method for property <tt>cancelDataTaskRetryFirstDelay</tt>.
     *
     * @return property value of cancelDataTaskRetryFirstDelay
     */
    @Override
    public int getCancelDataTaskRetryFirstDelay() {
        return cancelDataTaskRetryFirstDelay;
    }

    /**
     * Setter method for property <tt>cancelDataTaskRetryFirstDelay</tt>.
     *
     * @param cancelDataTaskRetryFirstDelay  value to be assigned to property cancelDataTaskRetryFirstDelay
     */
    public void setCancelDataTaskRetryFirstDelay(int cancelDataTaskRetryFirstDelay) {
        this.cancelDataTaskRetryFirstDelay = cancelDataTaskRetryFirstDelay;
    }

    /**
     * Getter method for property <tt>cancelDataTaskRetryIncrementDelay</tt>.
     *
     * @return property value of cancelDataTaskRetryIncrementDelay
     */
    @Override
    public long getCancelDataTaskRetryIncrementDelay() {
        return cancelDataTaskRetryIncrementDelay;
    }

    /**
     * Setter method for property <tt>cancelDataTaskRetryIncrementDelay</tt>.
     *
     * @param cancelDataTaskRetryIncrementDelay  value to be assigned to property cancelDataTaskRetryIncrementDelay
     */
    public void setCancelDataTaskRetryIncrementDelay(long cancelDataTaskRetryIncrementDelay) {
        this.cancelDataTaskRetryIncrementDelay = cancelDataTaskRetryIncrementDelay;
    }

    /**
     * Getter method for property <tt>receivedDataMultiPushTaskRetryTimes</tt>.
     *
     * @return property value of receivedDataMultiPushTaskRetryTimes
     */
    @Override
    public int getReceivedDataMultiPushTaskRetryTimes() {
        return receivedDataMultiPushTaskRetryTimes;
    }

    /**
     * Setter method for property <tt>receivedDataMultiPushTaskRetryTimes</tt>.
     *
     * @param receivedDataMultiPushTaskRetryTimes value to be assigned to property receivedDataMultiPushTaskRetryTimes
     */
    public void setReceivedDataMultiPushTaskRetryTimes(int receivedDataMultiPushTaskRetryTimes) {
        this.receivedDataMultiPushTaskRetryTimes = receivedDataMultiPushTaskRetryTimes;
    }

    /**
     * Getter method for property <tt>dataChangeFetchTaskRetryTimes</tt>.
     *
     * @return property value of dataChangeFetchTaskRetryTimes
     */
    @Override
    public int getDataChangeFetchTaskRetryTimes() {
        return dataChangeFetchTaskRetryTimes;
    }

    /**
     * Setter method for property <tt>dataChangeFetchTaskRetryTimes</tt>.
     *
     * @param dataChangeFetchTaskRetryTimes value to be assigned to property dataChangeFetchTaskRetryTimes
     */
    public void setDataChangeFetchTaskRetryTimes(int dataChangeFetchTaskRetryTimes) {
        this.dataChangeFetchTaskRetryTimes = dataChangeFetchTaskRetryTimes;
    }

    /**
     * Getter method for property <tt>subscriberRegisterFetchRetryTimes</tt>.
     *
     * @return property value of subscriberRegisterFetchRetryTimes
     */
    @Override
    public int getSubscriberRegisterFetchRetryTimes() {
        return subscriberRegisterFetchRetryTimes;
    }

    /**
     * Setter method for property <tt>subscriberRegisterFetchRetryTimes</tt>.
     *
     * @param subscriberRegisterFetchRetryTimes value to be assigned to property subscriberRegisterFetchRetryTimes
     */
    public void setSubscriberRegisterFetchRetryTimes(int subscriberRegisterFetchRetryTimes) {
        this.subscriberRegisterFetchRetryTimes = subscriberRegisterFetchRetryTimes;
    }

    /**
     * Getter method for property <tt>sessionRegisterDataServerTaskRetryTimes</tt>.
     *
     * @return property value of sessionRegisterDataServerTaskRetryTimes
     */
    @Override
    public int getSessionRegisterDataServerTaskRetryTimes() {
        return sessionRegisterDataServerTaskRetryTimes;
    }

    /**
     * Setter method for property <tt>sessionRegisterDataServerTaskRetryTimes</tt>.
     *
     * @param sessionRegisterDataServerTaskRetryTimes  value to be assigned to property sessionRegisterDataServerTaskRetryTimes
     */
    public void setSessionRegisterDataServerTaskRetryTimes(int sessionRegisterDataServerTaskRetryTimes) {
        this.sessionRegisterDataServerTaskRetryTimes = sessionRegisterDataServerTaskRetryTimes;
    }

    /**
     * Getter method for property <tt>clientNodeExchangeTimeOut</tt>.
     *
     * @return property value of clientNodeExchangeTimeOut
     */
    @Override
    public int getClientNodeExchangeTimeOut() {
        return clientNodeExchangeTimeOut;
    }

    /**
     * Setter method for property <tt>clientNodeExchangeTimeOut</tt>.
     *
     * @param clientNodeExchangeTimeOut value to be assigned to property clientNodeExchangeTimeOut
     */
    public void setClientNodeExchangeTimeOut(int clientNodeExchangeTimeOut) {
        this.clientNodeExchangeTimeOut = clientNodeExchangeTimeOut;
    }

    /**
     * Getter method for property <tt>dataNodeExchangeTimeOut</tt>.
     *
     * @return property value of dataNodeExchangeTimeOut
     */
    @Override
    public int getDataNodeExchangeTimeOut() {
        return dataNodeExchangeTimeOut;
    }

    /**
     * Setter method for property <tt>dataNodeExchangeTimeOut</tt>.
     *
     * @param dataNodeExchangeTimeOut value to be assigned to property dataNodeExchangeTimeOut
     */
    public void setDataNodeExchangeTimeOut(int dataNodeExchangeTimeOut) {
        this.dataNodeExchangeTimeOut = dataNodeExchangeTimeOut;
    }

    /**
     * Getter method for property <tt>metaServerPort</tt>.
     *
     * @return property value of metaServerPort
     */
    @Override
    public int getMetaServerPort() {
        return metaServerPort;
    }

    /**
     * Setter method for property <tt>metaServerPort</tt>.
     *
     * @param metaServerPort value to be assigned to property metaServerPort
     */
    public void setMetaServerPort(int metaServerPort) {
        this.metaServerPort = metaServerPort;
    }

    @Override
    public String getSessionServerRegion() {
        if (commonConfig != null) {
            String region = commonConfig.getLocalRegion();
            if (region != null && !region.isEmpty()) {
                return commonConfig.getLocalRegion().toUpperCase();
            }
        }

        if (sessionServerRegion != null) {
            sessionServerRegion = sessionServerRegion.toUpperCase();
        }
        return sessionServerRegion;
    }

    /**
     * Setter method for property <tt>sessionServerRegion</tt>.
     *
     * @param sessionServerRegion value to be assigned to property sessionServerRegion
     */
    public void setSessionServerRegion(String sessionServerRegion) {
        if (sessionServerRegion != null) {
            sessionServerRegion = sessionServerRegion.toUpperCase();
        }
        this.sessionServerRegion = sessionServerRegion;
    }

    @Override
    public String getSessionServerDataCenter() {
        if (commonConfig != null) {
            String dataCenter = commonConfig.getLocalDataCenter();
            if (dataCenter != null && !dataCenter.isEmpty()) {
                return commonConfig.getLocalDataCenter();
            }
        }

        return sessionServerDataCenter;
    }

    /**
     * Setter method for property <tt>sessionServerDataCenter</tt>.
     *
     * @param sessionServerDataCenter value to be assigned to property sessionServerDataCenter
     */
    public void setSessionServerDataCenter(String sessionServerDataCenter) {
        this.sessionServerDataCenter = sessionServerDataCenter;
    }

    /**
     * Getter method for property <tt>metaNodeExchangeTimeOut</tt>.
     *
     * @return property value of metaNodeExchangeTimeOut
     */
    @Override
    public int getMetaNodeExchangeTimeOut() {
        return metaNodeExchangeTimeOut;
    }

    /**
     * Setter method for property <tt>metaNodeExchangeTimeOut</tt>.
     *
     * @param metaNodeExchangeTimeOut value to be assigned to property metaNodeExchangeTimeOut
     */
    public void setMetaNodeExchangeTimeOut(int metaNodeExchangeTimeOut) {
        this.metaNodeExchangeTimeOut = metaNodeExchangeTimeOut;
    }

    /**
     * Getter method for property <tt>dataServerPort</tt>.
     *
     * @return property value of dataServerPort
     */
    @Override
    public int getDataServerPort() {
        return dataServerPort;
    }

    /**
     * Setter method for property <tt>dataServerPort</tt>.
     *
     * @param dataServerPort value to be assigned to property dataServerPort
     */
    public void setDataServerPort(int dataServerPort) {
        this.dataServerPort = dataServerPort;
    }

    /**
     * Getter method for property <tt>httpServerPort</tt>.
     *
     * @return property value of httpServerPort
     */
    @Override
    public int getHttpServerPort() {
        return httpServerPort;
    }

    /**
     * Setter method for property <tt>httpServerPort</tt>.
     *
     * @param httpServerPort value to be assigned to property httpServerPort
     */
    public void setHttpServerPort(int httpServerPort) {
        this.httpServerPort = httpServerPort;
    }

    /**
     * Getter method for property <tt>numberOfReplicas</tt>.
     *
     * @return property value of numberOfReplicas
     */
    @Override
    public int getNumberOfReplicas() {
        return numberOfReplicas;
    }

    /**
     * Setter method for property <tt>numberOfReplicas</tt>.
     *
     * @param numberOfReplicas value to be assigned to property numberOfReplicas
     */
    public void setNumberOfReplicas(int numberOfReplicas) {
        this.numberOfReplicas = numberOfReplicas;
    }

    /**
     * Getter method for property <tt>schedulerGetSessionNodeTimeout</tt>.
     *
     * @return property value of schedulerGetSessionNodeTimeout
     */
    @Override
    public int getSchedulerGetSessionNodeTimeout() {
        return schedulerGetSessionNodeTimeout;
    }

    /**
     * Setter method for property <tt>schedulerGetSessionNodeTimeout</tt>.
     *
     * @param schedulerGetSessionNodeTimeout value to be assigned to property schedulerGetSessionNodeTimeout
     */
    public void setSchedulerGetSessionNodeTimeout(int schedulerGetSessionNodeTimeout) {
        this.schedulerGetSessionNodeTimeout = schedulerGetSessionNodeTimeout;
    }

    /**
     * Getter method for property <tt>schedulerGetSessionNodeFirstDelay</tt>.
     *
     * @return property value of schedulerGetSessionNodeFirstDelay
     */
    @Override
    public int getSchedulerGetSessionNodeFirstDelay() {
        return schedulerGetSessionNodeFirstDelay;
    }

    /**
     * Setter method for property <tt>schedulerGetSessionNodeFirstDelay</tt>.
     *
     * @param schedulerGetSessionNodeFirstDelay value to be assigned to property schedulerGetSessionNodeFirstDelay
     */
    public void setSchedulerGetSessionNodeFirstDelay(int schedulerGetSessionNodeFirstDelay) {
        this.schedulerGetSessionNodeFirstDelay = schedulerGetSessionNodeFirstDelay;
    }

    /**
     * Getter method for property <tt>schedulerGetSessionNodeExpBackOffBound</tt>.
     *
     * @return property value of schedulerGetSessionNodeExpBackOffBound
     */
    @Override
    public int getSchedulerGetSessionNodeExpBackOffBound() {
        return schedulerGetSessionNodeExpBackOffBound;
    }

    /**
     * Setter method for property <tt>schedulerGetSessionNodeExpBackOffBound</tt>.
     *
     * @param schedulerGetSessionNodeExpBackOffBound value to be assigned to property schedulerGetSessionNodeExpBackOffBound
     */
    public void setSchedulerGetSessionNodeExpBackOffBound(int schedulerGetSessionNodeExpBackOffBound) {
        this.schedulerGetSessionNodeExpBackOffBound = schedulerGetSessionNodeExpBackOffBound;
    }

    /**
     * Getter method for property <tt>schedulerConnectMetaTimeout</tt>.
     *
     * @return property value of schedulerConnectMetaTimeout
     */
    @Override
    public int getSchedulerConnectMetaTimeout() {
        return schedulerConnectMetaTimeout;
    }

    /**
     * Getter method for property <tt>schedulerConnectMetaFirstDelay</tt>.
     *
     * @return property value of schedulerConnectMetaFirstDelay
     */
    @Override
    public int getSchedulerConnectMetaFirstDelay() {
        return schedulerConnectMetaFirstDelay;
    }

    /**
     * Getter method for property <tt>schedulerConnectMetaExpBackOffBound</tt>.
     *
     * @return property value of schedulerConnectMetaExpBackOffBound
     */
    @Override
    public int getSchedulerConnectMetaExpBackOffBound() {
        return schedulerConnectMetaExpBackOffBound;
    }

    /**
     * Setter method for property <tt>schedulerConnectMetaTimeout</tt>.
     *
     * @param schedulerConnectMetaTimeout  value to be assigned to property schedulerConnectMetaTimeout
     */
    public void setSchedulerConnectMetaTimeout(int schedulerConnectMetaTimeout) {
        this.schedulerConnectMetaTimeout = schedulerConnectMetaTimeout;
    }

    /**
     * Setter method for property <tt>schedulerConnectMetaFirstDelay</tt>.
     *
     * @param schedulerConnectMetaFirstDelay  value to be assigned to property schedulerConnectMetaFirstDelay
     */
    public void setSchedulerConnectMetaFirstDelay(int schedulerConnectMetaFirstDelay) {
        this.schedulerConnectMetaFirstDelay = schedulerConnectMetaFirstDelay;
    }

    /**
     * Setter method for property <tt>schedulerConnectMetaExpBackOffBound</tt>.
     *
     * @param schedulerConnectMetaExpBackOffBound  value to be assigned to property schedulerConnectMetaExpBackOffBound
     */
    public void setSchedulerConnectMetaExpBackOffBound(int schedulerConnectMetaExpBackOffBound) {
        this.schedulerConnectMetaExpBackOffBound = schedulerConnectMetaExpBackOffBound;
    }

    @Override
    public int getSchedulerConnectDataTimeout() {
        return schedulerConnectDataTimeout;
    }

    /**
     * Setter method for property <tt>schedulerConnectDataTimeout</tt>.
     *
     * @param schedulerConnectDataTimeout  value to be assigned to property schedulerConnectDataTimeout
     */
    public void setSchedulerConnectDataTimeout(int schedulerConnectDataTimeout) {
        this.schedulerConnectDataTimeout = schedulerConnectDataTimeout;
    }

    @Override
    public int getSchedulerConnectDataFirstDelay() {
        return schedulerConnectDataFirstDelay;
    }

    /**
     * Setter method for property <tt>schedulerConnectDataFirstDelay</tt>.
     *
     * @param schedulerConnectDataFirstDelay  value to be assigned to property schedulerConnectDataFirstDelay
     */
    public void setSchedulerConnectDataFirstDelay(int schedulerConnectDataFirstDelay) {
        this.schedulerConnectDataFirstDelay = schedulerConnectDataFirstDelay;
    }

    @Override
    public int getSchedulerConnectDataExpBackOffBound() {
        return schedulerConnectDataExpBackOffBound;
    }

    /**
     * Setter method for property <tt>schedulerConnectDataExpBackOffBound</tt>.
     *
     * @param schedulerConnectDataExpBackOffBound  value to be assigned to property schedulerConnectDataExpBackOffBound
     */
    public void setSchedulerConnectDataExpBackOffBound(int schedulerConnectDataExpBackOffBound) {
        this.schedulerConnectDataExpBackOffBound = schedulerConnectDataExpBackOffBound;
    }

    /**
     * Getter method for property <tt>stopPushSwitch</tt>.
     *
     * @return property value of stopPushSwitch
     */
    @Override
    public boolean isStopPushSwitch() {
        return stopPushSwitch;
    }

    /**
     * Setter method for property <tt>stopPushSwitch</tt>.
     *
     * @param stopPushSwitch  value to be assigned to property stopPushSwitch
     */
    @Override
    public void setStopPushSwitch(boolean stopPushSwitch) {
        this.stopPushSwitch = stopPushSwitch;
    }

    /**
     * Getter method for property <tt>beginDataFetchTask</tt>.
     *
     * @return property value of beginDataFetchTask
     */
    @Override
    public boolean isBeginDataFetchTask() {
        return beginDataFetchTask;
    }

    /**
     * Setter method for property <tt>beginDataFetchTask</tt>.
     *
     * @param beginDataFetchTask  value to be assigned to property beginDataFetchTask
     */
    @Override
    public void setBeginDataFetchTask(boolean beginDataFetchTask) {
        this.beginDataFetchTask = beginDataFetchTask;
    }

    public String getInvalidForeverZones() {
        return invalidForeverZones;
    }

    /**
     * Setter method for property <tt>invalidForeverZones</tt>.
     *
     * @param invalidForeverZones  value to be assigned to property invalidForeverZones
     */
    public void setInvalidForeverZones(String invalidForeverZones) {
        this.invalidForeverZones = invalidForeverZones;
    }

    public String getInvalidIgnoreDataidRegex() {
        return invalidIgnoreDataidRegex;
    }

    /**
     * Setter method for property <tt>invalidIgnoreDataidRegex</tt>.
     *
     * @param invalidIgnoreDataidRegex  value to be assigned to property invalidIgnoreDataidRegex
     */
    public void setInvalidIgnoreDataidRegex(String invalidIgnoreDataidRegex) {
        this.invalidIgnoreDataidRegex = invalidIgnoreDataidRegex;
    }

    @Override
    public int getAccessDataExecutorMinPoolSize() {
        return accessDataExecutorMinPoolSize;
    }

    /**
     * Setter method for property <tt>accessDataExecutorMinPoolSize</tt>.
     *
     * @param accessDataExecutorMinPoolSize  value to be assigned to property accessDataExecutorMinPoolSize
     */
    public void setAccessDataExecutorMinPoolSize(int accessDataExecutorMinPoolSize) {
        this.accessDataExecutorMinPoolSize = accessDataExecutorMinPoolSize;
    }

    @Override
    public int getAccessDataExecutorMaxPoolSize() {
        return accessDataExecutorMaxPoolSize;
    }

    /**
     * Setter method for property <tt>accessDataExecutorMaxPoolSize</tt>.
     *
     * @param accessDataExecutorMaxPoolSize  value to be assigned to property accessDataExecutorMaxPoolSize
     */
    public void setAccessDataExecutorMaxPoolSize(int accessDataExecutorMaxPoolSize) {
        this.accessDataExecutorMaxPoolSize = accessDataExecutorMaxPoolSize;
    }

    @Override
    public int getAccessDataExecutorQueueSize() {
        return accessDataExecutorQueueSize;
    }

    /**
     * Setter method for property <tt>accessDataExecutorQueueSize</tt>.
     *
     * @param accessDataExecutorQueueSize  value to be assigned to property accessDataExecutorQueueSize
     */
    public void setAccessDataExecutorQueueSize(int accessDataExecutorQueueSize) {
        this.accessDataExecutorQueueSize = accessDataExecutorQueueSize;
    }

    @Override
    public long getAccessDataExecutorKeepAliveTime() {
        return accessDataExecutorKeepAliveTime;
    }

    /**
     * Setter method for property <tt>accessDataExecutorKeepAliveTime</tt>.
     *
     * @param accessDataExecutorKeepAliveTime  value to be assigned to property accessDataExecutorKeepAliveTime
     */
    public void setAccessDataExecutorKeepAliveTime(long accessDataExecutorKeepAliveTime) {
        this.accessDataExecutorKeepAliveTime = accessDataExecutorKeepAliveTime;
    }

    public String getPushEmptyDataDataIdPrefixes() {
        return pushEmptyDataDataIdPrefixes;
    }

    /**
     * Getter method for property <tt>dataChangeExecutorMinPoolSize</tt>.
     *
     * @return property value of dataChangeExecutorMinPoolSize
     */
    @Override
    public int getDataChangeExecutorMinPoolSize() {
        return dataChangeExecutorMinPoolSize;
    }

    /**
     * Getter method for property <tt>dataChangeExecutorMaxPoolSize</tt>.
     *
     * @return property value of dataChangeExecutorMaxPoolSize
     */
    @Override
    public int getDataChangeExecutorMaxPoolSize() {
        return dataChangeExecutorMaxPoolSize;
    }

    /**
     * Getter method for property <tt>dataChangeExecutorQueueSize</tt>.
     *
     * @return property value of dataChangeExecutorQueueSize
     */
    @Override
    public int getDataChangeExecutorQueueSize() {
        return dataChangeExecutorQueueSize;
    }

    /**
     * Getter method for property <tt>dataChangeExecutorKeepAliveTime</tt>.
     *
     * @return property value of dataChangeExecutorKeepAliveTime
     */
    @Override
    public long getDataChangeExecutorKeepAliveTime() {
        return dataChangeExecutorKeepAliveTime;
    }

    /**
     * Setter method for property <tt>dataChangeExecutorMinPoolSize</tt>.
     *
     * @param dataChangeExecutorMinPoolSize  value to be assigned to property dataChangeExecutorMinPoolSize
     */
    public void setDataChangeExecutorMinPoolSize(int dataChangeExecutorMinPoolSize) {
        this.dataChangeExecutorMinPoolSize = dataChangeExecutorMinPoolSize;
    }

    /**
     * Setter method for property <tt>dataChangeExecutorMaxPoolSize</tt>.
     *
     * @param dataChangeExecutorMaxPoolSize  value to be assigned to property dataChangeExecutorMaxPoolSize
     */
    public void setDataChangeExecutorMaxPoolSize(int dataChangeExecutorMaxPoolSize) {
        this.dataChangeExecutorMaxPoolSize = dataChangeExecutorMaxPoolSize;
    }

    /**
     * Setter method for property <tt>dataChangeExecutorQueueSize</tt>.
     *
     * @param dataChangeExecutorQueueSize  value to be assigned to property dataChangeExecutorQueueSize
     */
    public void setDataChangeExecutorQueueSize(int dataChangeExecutorQueueSize) {
        this.dataChangeExecutorQueueSize = dataChangeExecutorQueueSize;
    }

    /**
     * Setter method for property <tt>dataChangeExecutorKeepAliveTime</tt>.
     *
     * @param dataChangeExecutorKeepAliveTime  value to be assigned to property dataChangeExecutorKeepAliveTime
     */
    public void setDataChangeExecutorKeepAliveTime(long dataChangeExecutorKeepAliveTime) {
        this.dataChangeExecutorKeepAliveTime = dataChangeExecutorKeepAliveTime;
    }

    /**
     * Setter method for property <tt>pushEmptyDataDataIdPrefixes</tt>.
     *
     * @param pushEmptyDataDataIdPrefixes  value to be assigned to property pushEmptyDataDataIdPrefixes
     */
    public void setPushEmptyDataDataIdPrefixes(String pushEmptyDataDataIdPrefixes) {
        this.pushEmptyDataDataIdPrefixes = pushEmptyDataDataIdPrefixes;
    }

    /**
     * Getter method for property <tt>pushTaskExecutorMinPoolSize</tt>.
     *
     * @return property value of pushTaskExecutorMinPoolSize
     */
    @Override
    public int getPushTaskExecutorMinPoolSize() {
        return pushTaskExecutorMinPoolSize;
    }

    /**
     * Getter method for property <tt>pushTaskExecutorMaxPoolSize</tt>.
     *
     * @return property value of pushTaskExecutorMaxPoolSize
     */
    @Override
    public int getPushTaskExecutorMaxPoolSize() {
        return pushTaskExecutorMaxPoolSize;
    }

    /**
     * Getter method for property <tt>pushTaskExecutorQueueSize</tt>.
     *
     * @return property value of pushTaskExecutorQueueSize
     */
    @Override
    public int getPushTaskExecutorQueueSize() {
        return pushTaskExecutorQueueSize;
    }

    /**
     * Getter method for property <tt>pushTaskExecutorKeepAliveTime</tt>.
     *
     * @return property value of pushTaskExecutorKeepAliveTime
     */
    @Override
    public long getPushTaskExecutorKeepAliveTime() {
        return pushTaskExecutorKeepAliveTime;
    }

    /**
     * Setter method for property <tt>pushTaskExecutorMinPoolSize</tt>.
     *
     * @param pushTaskExecutorMinPoolSize  value to be assigned to property pushTaskExecutorMinPoolSize
     */
    public void setPushTaskExecutorMinPoolSize(int pushTaskExecutorMinPoolSize) {
        this.pushTaskExecutorMinPoolSize = pushTaskExecutorMinPoolSize;
    }

    /**
     * Setter method for property <tt>pushTaskExecutorMaxPoolSize</tt>.
     *
     * @param pushTaskExecutorMaxPoolSize  value to be assigned to property pushTaskExecutorMaxPoolSize
     */
    public void setPushTaskExecutorMaxPoolSize(int pushTaskExecutorMaxPoolSize) {
        this.pushTaskExecutorMaxPoolSize = pushTaskExecutorMaxPoolSize;
    }

    /**
     * Setter method for property <tt>pushTaskExecutorQueueSize</tt>.
     *
     * @param pushTaskExecutorQueueSize  value to be assigned to property pushTaskExecutorQueueSize
     */
    public void setPushTaskExecutorQueueSize(int pushTaskExecutorQueueSize) {
        this.pushTaskExecutorQueueSize = pushTaskExecutorQueueSize;
    }

    /**
     * Setter method for property <tt>pushTaskExecutorKeepAliveTime</tt>.
     *
     * @param pushTaskExecutorKeepAliveTime  value to be assigned to property pushTaskExecutorKeepAliveTime
     */
    public void setPushTaskExecutorKeepAliveTime(long pushTaskExecutorKeepAliveTime) {
        this.pushTaskExecutorKeepAliveTime = pushTaskExecutorKeepAliveTime;
    }

    public Set<String> getPushEmptyDataDataIdPrefixesSet() {
        if (pushEmptyDataDataIdPrefixesSet == null || pushEmptyDataDataIdPrefixesSet.isEmpty()) {
            Set<String> s = new HashSet<>();
            String[] arr = pushEmptyDataDataIdPrefixes.split(";");
            for (String str : arr) {
                if (str.trim().length() > 0) {
                    s.add(str);
                }
            }
            pushEmptyDataDataIdPrefixesSet = Collections.unmodifiableSet(s);
        }
        return pushEmptyDataDataIdPrefixesSet;
    }

    /**
     * Setter method for property <tt>pushEmptyDataDataIdPrefixesSet</tt>.
     *
     * @param pushEmptyDataDataIdPrefixesSet  value to be assigned to property pushEmptyDataDataIdPrefixesSet
     */
    public void setPushEmptyDataDataIdPrefixesSet(Set<String> pushEmptyDataDataIdPrefixesSet) {
        this.pushEmptyDataDataIdPrefixesSet = pushEmptyDataDataIdPrefixesSet;
    }

    /**
     * Getter method for property <tt>defaultSessionExecutorMinPoolSize</tt>.
     *
     * @return property value of defaultSessionExecutorMinPoolSize
     */
    public int getDefaultSessionExecutorMinPoolSize() {
        return defaultSessionExecutorMinPoolSize;
    }

    /**
     * Getter method for property <tt>defaultSessionExecutorMaxPoolSize</tt>.
     *
     * @return property value of defaultSessionExecutorMaxPoolSize
     */
    public int getDefaultSessionExecutorMaxPoolSize() {
        return defaultSessionExecutorMaxPoolSize;
    }

    /**
     * Getter method for property <tt>defaultSessionExecutorKeepAliveTime</tt>.
     *
     * @return property value of defaultSessionExecutorKeepAliveTime
     */
    public long getDefaultSessionExecutorKeepAliveTime() {
        return defaultSessionExecutorKeepAliveTime;
    }

    /**
     * Setter method for property <tt>defaultSessionExecutorMinPoolSize</tt>.
     *
     * @param defaultSessionExecutorMinPoolSize  value to be assigned to property defaultSessionExecutorMinPoolSize
     */
    public void setDefaultSessionExecutorMinPoolSize(int defaultSessionExecutorMinPoolSize) {
        this.defaultSessionExecutorMinPoolSize = defaultSessionExecutorMinPoolSize;
    }

    /**
     * Setter method for property <tt>defaultSessionExecutorMaxPoolSize</tt>.
     *
     * @param defaultSessionExecutorMaxPoolSize  value to be assigned to property defaultSessionExecutorMaxPoolSize
     */
    public void setDefaultSessionExecutorMaxPoolSize(int defaultSessionExecutorMaxPoolSize) {
        this.defaultSessionExecutorMaxPoolSize = defaultSessionExecutorMaxPoolSize;
    }

    /**
     * Setter method for property <tt>defaultSessionExecutorKeepAliveTime</tt>.
     *
     * @param defaultSessionExecutorKeepAliveTime  value to be assigned to property defaultSessionExecutorKeepAliveTime
     */
    public void setDefaultSessionExecutorKeepAliveTime(long defaultSessionExecutorKeepAliveTime) {
        this.defaultSessionExecutorKeepAliveTime = defaultSessionExecutorKeepAliveTime;
    }

    /**
     * Getter method for property <tt>disconnectClientExecutorMinPoolSize</tt>.
     *
     * @return property value of disconnectClientExecutorMinPoolSize
     */
    @Override
    public int getDisconnectClientExecutorMinPoolSize() {
        return disconnectClientExecutorMinPoolSize;
    }

    /**
     * Setter method for property <tt>disconnectClientExecutorMinPoolSize</tt>.
     *
     * @param disconnectClientExecutorMinPoolSize  value to be assigned to property disconnectClientExecutorMinPoolSize
     */
    public void setDisconnectClientExecutorMinPoolSize(int disconnectClientExecutorMinPoolSize) {
        this.disconnectClientExecutorMinPoolSize = disconnectClientExecutorMinPoolSize;
    }

    /**
     * Getter method for property <tt>disconnectClientExecutorMaxPoolSize</tt>.
     *
     * @return property value of disconnectClientExecutorMaxPoolSize
     */
    @Override
    public int getDisconnectClientExecutorMaxPoolSize() {
        return disconnectClientExecutorMaxPoolSize;
    }

    /**
     * Setter method for property <tt>disconnectClientExecutorMaxPoolSize</tt>.
     *
     * @param disconnectClientExecutorMaxPoolSize  value to be assigned to property disconnectClientExecutorMaxPoolSize
     */
    public void setDisconnectClientExecutorMaxPoolSize(int disconnectClientExecutorMaxPoolSize) {
        this.disconnectClientExecutorMaxPoolSize = disconnectClientExecutorMaxPoolSize;
    }

    /**
     * Getter method for property <tt>disconnectClientExecutorQueueSize</tt>.
     *
     * @return property value of disconnectClientExecutorQueueSize
     */
    @Override
    public int getDisconnectClientExecutorQueueSize() {
        return disconnectClientExecutorQueueSize;
    }

    /**
     * Setter method for property <tt>disconnectClientExecutorQueueSize</tt>.
     *
     * @param disconnectClientExecutorQueueSize  value to be assigned to property disconnectClientExecutorQueueSize
     */
    public void setDisconnectClientExecutorQueueSize(int disconnectClientExecutorQueueSize) {
        this.disconnectClientExecutorQueueSize = disconnectClientExecutorQueueSize;
    }

    /**
     * Getter method for property <tt>dataChangeFetchTaskMaxBufferSize</tt>.
     *
     * @return property value of dataChangeFetchTaskMaxBufferSize
     */
    @Override
    public int getDataChangeFetchTaskMaxBufferSize() {
        return dataChangeFetchTaskMaxBufferSize;
    }

    /**
     * Setter method for property <tt>dataChangeFetchTaskMaxBufferSize</tt>.
     *
     * @param dataChangeFetchTaskMaxBufferSize  value to be assigned to property dataChangeFetchTaskMaxBufferSize
     */
    public void setDataChangeFetchTaskMaxBufferSize(int dataChangeFetchTaskMaxBufferSize) {
        this.dataChangeFetchTaskMaxBufferSize = dataChangeFetchTaskMaxBufferSize;
    }

    /**
     * Getter method for property <tt>dataChangeFetchTaskWorkerSize</tt>.
     *
     * @return property value of dataChangeFetchTaskWorkerSize
     */
    @Override
    public int getDataChangeFetchTaskWorkerSize() {
        return dataChangeFetchTaskWorkerSize;
    }

    /**
     * Setter method for property <tt>dataChangeFetchTaskWorkerSize</tt>.
     *
     * @param dataChangeFetchTaskWorkerSize  value to be assigned to property dataChangeFetchTaskWorkerSize
     */
    public void setDataChangeFetchTaskWorkerSize(int dataChangeFetchTaskWorkerSize) {
        this.dataChangeFetchTaskWorkerSize = dataChangeFetchTaskWorkerSize;
    }

    /**
     * Getter method for property <tt>userDataPushRetryWheelTicksSize</tt>.
     *
     * @return property value of userDataPushRetryWheelTicksSize
     */
    @Override
    public int getUserDataPushRetryWheelTicksSize() {
        return userDataPushRetryWheelTicksSize;
    }

    /**
     * Setter method for property <tt>userDataPushRetryWheelTicksSize</tt>.
     *
     * @param userDataPushRetryWheelTicksSize  value to be assigned to property userDataPushRetryWheelTicksSize
     */
    public void setUserDataPushRetryWheelTicksSize(int userDataPushRetryWheelTicksSize) {
        this.userDataPushRetryWheelTicksSize = userDataPushRetryWheelTicksSize;
    }

    /**
     * Getter method for property <tt>userDataPushRetryWheelTicksDuration</tt>.
     *
     * @return property value of userDataPushRetryWheelTicksDuration
     */
    @Override
    public int getUserDataPushRetryWheelTicksDuration() {
        return userDataPushRetryWheelTicksDuration;
    }

    /**
     * Setter method for property <tt>userDataPushRetryWheelTicksDuration</tt>.
     *
     * @param userDataPushRetryWheelTicksDuration  value to be assigned to property userDataPushRetryWheelTicksDuration
     */
    public void setUserDataPushRetryWheelTicksDuration(int userDataPushRetryWheelTicksDuration) {
        this.userDataPushRetryWheelTicksDuration = userDataPushRetryWheelTicksDuration;
    }

    /**
     * Getter method for property <tt>pushDataTaskRetryFirstDelay</tt>.
     *
     * @return property value of pushDataTaskRetryFirstDelay
     */
    @Override
    public int getPushDataTaskRetryFirstDelay() {
        return pushDataTaskRetryFirstDelay;
    }

    /**
     * Setter method for property <tt>pushDataTaskRetryFirstDelay</tt>.
     *
     * @param pushDataTaskRetryFirstDelay  value to be assigned to property pushDataTaskRetryFirstDelay
     */
    public void setPushDataTaskRetryFirstDelay(int pushDataTaskRetryFirstDelay) {
        this.pushDataTaskRetryFirstDelay = pushDataTaskRetryFirstDelay;
    }

    /**
     * Getter method for property <tt>pushDataTaskRetryIncrementDelay</tt>.
     *
     * @return property value of pushDataTaskRetryIncrementDelay
     */
    @Override
    public long getPushDataTaskRetryIncrementDelay() {
        return pushDataTaskRetryIncrementDelay;
    }

    /**
     * Setter method for property <tt>pushDataTaskRetryIncrementDelay</tt>.
     *
     * @param pushDataTaskRetryIncrementDelay  value to be assigned to property pushDataTaskRetryIncrementDelay
     */
    public void setPushDataTaskRetryIncrementDelay(long pushDataTaskRetryIncrementDelay) {
        this.pushDataTaskRetryIncrementDelay = pushDataTaskRetryIncrementDelay;
    }

    @Override
    public boolean isInvalidForeverZone(String zoneId) {

        String[] zoneNameArr = getInvalidForeverZones().split(";");
        if (invalidForeverZonesSet == null) {
            invalidForeverZonesSet = new HashSet<>();
            for (String str : zoneNameArr) {
                if (str.trim().length() > 0) {
                    invalidForeverZonesSet.add(str);
                }
            }
        }

        return invalidForeverZonesSet.contains(zoneId);
    }

    @Override
    public boolean isInvalidIgnored(String dataId) {

        String invalidIgnoreDataidRegex = getInvalidIgnoreDataidRegex();
        if (null != invalidIgnoreDataidRegex && !invalidIgnoreDataidRegex.isEmpty()) {
            invalidIgnoreDataIdPattern = Pattern.compile(invalidIgnoreDataidRegex);
        }

        return null != invalidIgnoreDataIdPattern
               && invalidIgnoreDataIdPattern.matcher(dataId).find();
    }

    public static int cpus() {
        return Runtime.getRuntime().availableProcessors();
    }
}