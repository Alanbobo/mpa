package com.commandcenter.common.activemq.model;

/**
 * @author r25437
 * @create 2018-10-24 11:20
 * @desc ICC警综反馈信息模版
 **/
public class IccCaseFeedBackModel {

    /**
     * 反馈id
     */
    private String id;
    /**
     * 警情id
     */
    private String alarm_id;
    /**
     * 处警id
     */
    private String process_id;
    /**
     * 反馈类型
     */
    private String result_type;
    /**
     * 处理结果内容
     */
    private String result_content;
    /**
     * 领导指示
     */
    private String leader_instruction;
    /**
     * 反馈单状态
     */
    private String state;
    /**
     * 反馈单编辑时间
     */
    private String time_edit;
    /**
     * 反馈单提交时间
     */
    private String time_submit;
    /**
     * 反馈单到达时间
     */
    private String time_arrived;
    /**
     * 反馈单签收时间
     */
    private String time_signed;
    /**
     * 实际派出警力时间
     */
    private String time_police_dispatch;
    /**
     * 警力到达现场时间
     */
    private String time_police_arrived;
    /**
     * 警情实际发生时间
     */
    private String actual_occur_time;
    /**
     * 警情实际发生地址
     */
    private String actual_occur_addr;
    /**
     * 反馈单位行政区划
     */
    private String feedback_dept_district_code;
    /**
     * 反馈单位编码
     */
    private String feedback_dept_code;
    /**
     * 反馈单位名称
     */
    private String feedback_dept_name;
    /**
     * 反馈人警号
     */
    private String feedback_code;
    /**
     * 反馈人姓名
     */
    private String feedback_name;
    /**
     * 反馈单位领导警号
     */
    private String feedback_leader_code;
    /**
     * 反馈单位领导姓名
     */
    private String feedback_leader_name;
    /**
     * 处警单位行政区划
     */
    private String process_dept_district_code;
    /**
     * 处警单位编码
     */
    private String process_dept_code;
    /**
     * 处警单位名称
     */
    private String process_dept_name;
    /**
     * 处警人警号
     */
    private String process_code;
    /**
     * 处警人时间
     */
    private String process_name;
    /**
     * 处警单位领导警号
     */
    private String process_leader_code;
    /**
     * 处警单位领导姓名
     */
    private String process_leader_name;
    /**
     * 派警单位行政区划
     */
    private String dispatch_dept_district_code;
    /**
     * 派警单位编码
     */
    private String dispatch_dept_code;
    /**
     * 派警单位名称
     */
    private String dispatch_dept_name;
    /**
     * 派警人警号
     */
    private String dispatch_code;
    /**
     * 派警人姓名
     */
    private String dispatch_name;
    /**
     * 派警单位领导警号
     */
    private String dispatch_leader_code;
    /**
     * 派警单位领导姓名
     */
    private String dispatch_leader_name;
    /**
     * 第一个当事人证件号
     */
    private String person_id;
    /**
     * 第一个当事人证件类型
     */
    private String person_id_type;
    /**
     * 第一个当事人国籍
     */
    private String person_nationality;
    /**
     * 第一个当事人姓名
     */
    private String person_name;
    /**
     * 第二个当事人证件号
     */
    private String person_slave_id;
    /**
     * 第二个当事人证件类型
     */
    private String person_slave_id_type;
    /**
     * 第二个当事人国籍
     */
    private String person_slave_nationality;
    /**
     * 第二个当事人姓名
     */
    private String person_slave_name;
    /**
     * 警情报警号码类型
     */
    private String alarm_called_no_type;
    /**
     * 警情一级类型
     */
    private String alarm_first_type;
    /**
     * 警情二级类型
     */
    private String alarm_second_type;
    /**
     * 警情三级类型
     */
    private String alarm_third_type;
    /**
     * 警情四级类型
     */
    private String alarm_fourth_type;
    /**
     * 警情发生地单位名称
     */
    private String alarm_addr_dept_name;
    /**
     * 警情发生地一级类型
     */
    private String alarm_addr_first_type;
    /**
     * 警情发生地二级类型
     */
    private String alarm_addr_second_type;
    /**
     * 警情发生地经度
     */
    private String alarm_longitude;
    /**
     * 警情发生地纬度
     */
    private String alarm_latitude;
    /**
     * 警情发生地区域类型;如:郊区、城区
     */
    private String alarm_region_type;
    /**
     * 警情发生地场所类型代码；如：居民区、车站码头等
     */
    private String alarm_location_type;
    /**
     * 抓获人数
     */
    private String people_num_capture;
    /**
     * 救助人数
     */
    private String people_num_rescue;
    /**
     * 轻伤人数
     */
    private String people_num_slight_injury;
    /**
     * 重伤人数
     */
    private String people_num_serious_injury;
    /**
     * 死亡人数
     */
    private String people_num_death;
    /**
     * 出动警力数
     */
    private String police_num_dispatch;
    /**
     * 出动车辆数
     */
    private String police_car_num_dispatch;
    /**
     * 经济损失
     */
    private String economy_loss;
    /**
     * 挽回经济损失
     */
    private String retrieve_economy_loss;
    /**
     * 火灾警情：火灾扑灭时间
     */
    private String fire_put_out_time;
    /**
     * 火灾警情：起火源类型，如家具
     */
    private String fire_source_type;
    /**
     * 火灾警情：起火区域类型，如阳台
     */
    private String fire_region_type;
    /**
     * 火灾警情：火灾原因一级类型
     */
    private String fire_cause_first_type;
    /**
     * 火灾警情：火灾原因二级类型
     */
    private String fire_cause_second_type;
    /**
     * 火灾警情：火灾原因三级类型
     */
    private String fire_cause_third_type;
    /**
     * 火灾警情：燃烧面积
     */
    private String fire_area;
    /**
     * 交通警情：道路等级
     */
    private String traffic_road_level;
    /**
     * 交通警情：警情等级
     */
    private String traffic_accident_level;
    /**
     * 交通警情：第一个事故车辆车牌号
     */
    private String traffic_vehicle_no;
    /**
     * 交通警情：第一个事故车辆类型
     */
    private String traffic_vehicle_type;
    /**
     * 交通警情：第二个事故车辆车牌号
     */
    private String traffic_slave_vehicle_no;
    /**
     * 交通警情：第二个事故车辆类型
     */
    private String traffic_slave_vehicle_type;
    /**
     * 是否破获刑事案件 0: false 1: true
     */
    private String is_solve_crown;
    /**
     * 是否查处治安案件 0: false 1: true
     */
    private String is_solve_public_security;
    /**
     * 事件类型，逗号隔开
     */
    private String event_type;
    /**
     * 媒体资源类型
     */
    private String media_type;
    /**
     * 媒体资源标识 0:文本 1:图片 2:视频 3:音频 4:自定义
     */
    private String media_id;
    /**
     * 创建人
     */
    private String create_user;
    /**
     * 创建时间
     */
    private String create_time;
    /**
     * 修改人
     */
    private String update_user;
    /**
     * 修改时间
     */
    private String update_time;

    /**
     * 接警人警号
     */
    private String receving_code;

    /**
     * 接警人姓名
     */
    private String receving_name;

    /**
     * 火灾警情：起火建筑类别一级代码
     */
    private String fire_building_first_type;

    /**
     * 火灾警情：起火建筑类别二级代码
     */
    private String fire_building_second_type;

    /**
     * 火灾警情：起火建筑类别三级代码
     */
    private String fire_building_third_type;

    private String is_solve_public_secruity;

    public String getAlarm_fourth_type() {
        return alarm_fourth_type;
    }

    public void setAlarm_fourth_type(String alarm_fourth_type) {
        this.alarm_fourth_type = alarm_fourth_type;
    }

    /**
     * 接警时间
     */
    private String case_time;

    public String getCase_time() {
        return case_time;
    }

    public void setCase_time(String case_time) {
        this.case_time = case_time;
    }

    public String getIs_solve_public_secruity() {
        return is_solve_public_secruity;
    }

    public void setIs_solve_public_secruity(String is_solve_public_secruity) {
        this.is_solve_public_secruity = is_solve_public_secruity;
    }

    public String getReceving_code() {
        return receving_code;
    }

    public void setReceving_code(String receving_code) {
        this.receving_code = receving_code;
    }

    public String getReceving_name() {
        return receving_name;
    }

    public void setReceving_name(String receving_name) {
        this.receving_name = receving_name;
    }

    public String getFire_building_first_type() {
        return fire_building_first_type;
    }

    public void setFire_building_first_type(String fire_building_first_type) {
        this.fire_building_first_type = fire_building_first_type;
    }

    public String getFire_building_second_type() {
        return fire_building_second_type;
    }

    public void setFire_building_second_type(String fire_building_second_type) {
        this.fire_building_second_type = fire_building_second_type;
    }

    public String getFire_building_third_type() {
        return fire_building_third_type;
    }

    public void setFire_building_third_type(String fire_building_third_type) {
        this.fire_building_third_type = fire_building_third_type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAlarm_id() {
        return alarm_id;
    }

    public void setAlarm_id(String alarm_id) {
        this.alarm_id = alarm_id;
    }

    public String getProcess_id() {
        return process_id;
    }

    public void setProcess_id(String process_id) {
        this.process_id = process_id;
    }

    public String getResult_type() {
        return result_type;
    }

    public void setResult_type(String result_type) {
        this.result_type = result_type;
    }

    public String getResult_content() {
        return result_content;
    }

    public void setResult_content(String result_content) {
        this.result_content = result_content;
    }

    public String getLeader_instruction() {
        return leader_instruction;
    }

    public void setLeader_instruction(String leader_instruction) {
        this.leader_instruction = leader_instruction;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTime_edit() {
        return time_edit;
    }

    public void setTime_edit(String time_edit) {
        this.time_edit = time_edit;
    }

    public String getTime_submit() {
        return time_submit;
    }

    public void setTime_submit(String time_submit) {
        this.time_submit = time_submit;
    }

    public String getTime_arrived() {
        return time_arrived;
    }

    public void setTime_arrived(String time_arrived) {
        this.time_arrived = time_arrived;
    }

    public String getTime_signed() {
        return time_signed;
    }

    public void setTime_signed(String time_signed) {
        this.time_signed = time_signed;
    }

    public String getTime_police_dispatch() {
        return time_police_dispatch;
    }

    public void setTime_police_dispatch(String time_police_dispatch) {
        this.time_police_dispatch = time_police_dispatch;
    }

    public String getTime_police_arrived() {
        return time_police_arrived;
    }

    public void setTime_police_arrived(String time_police_arrived) {
        this.time_police_arrived = time_police_arrived;
    }

    public String getActual_occur_time() {
        return actual_occur_time;
    }

    public void setActual_occur_time(String actual_occur_time) {
        this.actual_occur_time = actual_occur_time;
    }

    public String getActual_occur_addr() {
        return actual_occur_addr;
    }

    public void setActual_occur_addr(String actual_occur_addr) {
        this.actual_occur_addr = actual_occur_addr;
    }

    public String getFeedback_dept_district_code() {
        return feedback_dept_district_code;
    }

    public void setFeedback_dept_district_code(String feedback_dept_district_code) {
        this.feedback_dept_district_code = feedback_dept_district_code;
    }

    public String getFeedback_dept_code() {
        return feedback_dept_code;
    }

    public void setFeedback_dept_code(String feedback_dept_code) {
        this.feedback_dept_code = feedback_dept_code;
    }

    public String getFeedback_dept_name() {
        return feedback_dept_name;
    }

    public void setFeedback_dept_name(String feedback_dept_name) {
        this.feedback_dept_name = feedback_dept_name;
    }

    public String getFeedback_code() {
        return feedback_code;
    }

    public void setFeedback_code(String feedback_code) {
        this.feedback_code = feedback_code;
    }

    public String getFeedback_name() {
        return feedback_name;
    }

    public void setFeedback_name(String feedback_name) {
        this.feedback_name = feedback_name;
    }

    public String getFeedback_leader_code() {
        return feedback_leader_code;
    }

    public void setFeedback_leader_code(String feedback_leader_code) {
        this.feedback_leader_code = feedback_leader_code;
    }

    public String getFeedback_leader_name() {
        return feedback_leader_name;
    }

    public void setFeedback_leader_name(String feedback_leader_name) {
        this.feedback_leader_name = feedback_leader_name;
    }

    public String getProcess_dept_district_code() {
        return process_dept_district_code;
    }

    public void setProcess_dept_district_code(String process_dept_district_code) {
        this.process_dept_district_code = process_dept_district_code;
    }

    public String getProcess_dept_code() {
        return process_dept_code;
    }

    public void setProcess_dept_code(String process_dept_code) {
        this.process_dept_code = process_dept_code;
    }

    public String getProcess_dept_name() {
        return process_dept_name;
    }

    public void setProcess_dept_name(String process_dept_name) {
        this.process_dept_name = process_dept_name;
    }

    public String getProcess_code() {
        return process_code;
    }

    public void setProcess_code(String process_code) {
        this.process_code = process_code;
    }

    public String getProcess_name() {
        return process_name;
    }

    public void setProcess_name(String process_name) {
        this.process_name = process_name;
    }

    public String getProcess_leader_code() {
        return process_leader_code;
    }

    public void setProcess_leader_code(String process_leader_code) {
        this.process_leader_code = process_leader_code;
    }

    public String getProcess_leader_name() {
        return process_leader_name;
    }

    public void setProcess_leader_name(String process_leader_name) {
        this.process_leader_name = process_leader_name;
    }

    public String getDispatch_dept_district_code() {
        return dispatch_dept_district_code;
    }

    public void setDispatch_dept_district_code(String dispatch_dept_district_code) {
        this.dispatch_dept_district_code = dispatch_dept_district_code;
    }

    public String getDispatch_dept_code() {
        return dispatch_dept_code;
    }

    public void setDispatch_dept_code(String dispatch_dept_code) {
        this.dispatch_dept_code = dispatch_dept_code;
    }

    public String getDispatch_dept_name() {
        return dispatch_dept_name;
    }

    public void setDispatch_dept_name(String dispatch_dept_name) {
        this.dispatch_dept_name = dispatch_dept_name;
    }

    public String getDispatch_code() {
        return dispatch_code;
    }

    public void setDispatch_code(String dispatch_code) {
        this.dispatch_code = dispatch_code;
    }

    public String getDispatch_name() {
        return dispatch_name;
    }

    public void setDispatch_name(String dispatch_name) {
        this.dispatch_name = dispatch_name;
    }

    public String getDispatch_leader_code() {
        return dispatch_leader_code;
    }

    public void setDispatch_leader_code(String dispatch_leader_code) {
        this.dispatch_leader_code = dispatch_leader_code;
    }

    public String getDispatch_leader_name() {
        return dispatch_leader_name;
    }

    public void setDispatch_leader_name(String dispatch_leader_name) {
        this.dispatch_leader_name = dispatch_leader_name;
    }

    public String getPerson_id() {
        return person_id;
    }

    public void setPerson_id(String person_id) {
        this.person_id = person_id;
    }

    public String getPerson_id_type() {
        return person_id_type;
    }

    public void setPerson_id_type(String person_id_type) {
        this.person_id_type = person_id_type;
    }

    public String getPerson_nationality() {
        return person_nationality;
    }

    public void setPerson_nationality(String person_nationality) {
        this.person_nationality = person_nationality;
    }

    public String getPerson_name() {
        return person_name;
    }

    public void setPerson_name(String person_name) {
        this.person_name = person_name;
    }

    public String getPerson_slave_id() {
        return person_slave_id;
    }

    public void setPerson_slave_id(String person_slave_id) {
        this.person_slave_id = person_slave_id;
    }

    public String getPerson_slave_id_type() {
        return person_slave_id_type;
    }

    public void setPerson_slave_id_type(String person_slave_id_type) {
        this.person_slave_id_type = person_slave_id_type;
    }

    public String getPerson_slave_nationality() {
        return person_slave_nationality;
    }

    public void setPerson_slave_nationality(String person_slave_nationality) {
        this.person_slave_nationality = person_slave_nationality;
    }

    public String getPerson_slave_name() {
        return person_slave_name;
    }

    public void setPerson_slave_name(String person_slave_name) {
        this.person_slave_name = person_slave_name;
    }

    public String getAlarm_called_no_type() {
        return alarm_called_no_type;
    }

    public void setAlarm_called_no_type(String alarm_called_no_type) {
        this.alarm_called_no_type = alarm_called_no_type;
    }

    public String getAlarm_first_type() {
        return alarm_first_type;
    }

    public void setAlarm_first_type(String alarm_first_type) {
        this.alarm_first_type = alarm_first_type;
    }

    public String getAlarm_second_type() {
        return alarm_second_type;
    }

    public void setAlarm_second_type(String alarm_second_type) {
        this.alarm_second_type = alarm_second_type;
    }

    public String getAlarm_third_type() {
        return alarm_third_type;
    }

    public void setAlarm_third_type(String alarm_third_type) {
        this.alarm_third_type = alarm_third_type;
    }

    public String getAlarm_addr_dept_name() {
        return alarm_addr_dept_name;
    }

    public void setAlarm_addr_dept_name(String alarm_addr_dept_name) {
        this.alarm_addr_dept_name = alarm_addr_dept_name;
    }

    public String getAlarm_addr_first_type() {
        return alarm_addr_first_type;
    }

    public void setAlarm_addr_first_type(String alarm_addr_first_type) {
        this.alarm_addr_first_type = alarm_addr_first_type;
    }

    public String getAlarm_addr_second_type() {
        return alarm_addr_second_type;
    }

    public void setAlarm_addr_second_type(String alarm_addr_second_type) {
        this.alarm_addr_second_type = alarm_addr_second_type;
    }

    public String getAlarm_longitude() {
        return alarm_longitude;
    }

    public void setAlarm_longitude(String alarm_longitude) {
        this.alarm_longitude = alarm_longitude;
    }

    public String getAlarm_latitude() {
        return alarm_latitude;
    }

    public void setAlarm_latitude(String alarm_latitude) {
        this.alarm_latitude = alarm_latitude;
    }

    public String getAlarm_region_type() {
        return alarm_region_type;
    }

    public void setAlarm_region_type(String alarm_region_type) {
        this.alarm_region_type = alarm_region_type;
    }

    public String getAlarm_location_type() {
        return alarm_location_type;
    }

    public void setAlarm_location_type(String alarm_location_type) {
        this.alarm_location_type = alarm_location_type;
    }

    public String getPeople_num_capture() {
        return people_num_capture;
    }

    public void setPeople_num_capture(String people_num_capture) {
        this.people_num_capture = people_num_capture;
    }

    public String getPeople_num_rescue() {
        return people_num_rescue;
    }

    public void setPeople_num_rescue(String people_num_rescue) {
        this.people_num_rescue = people_num_rescue;
    }

    public String getPeople_num_slight_injury() {
        return people_num_slight_injury;
    }

    public void setPeople_num_slight_injury(String people_num_slight_injury) {
        this.people_num_slight_injury = people_num_slight_injury;
    }

    public String getPeople_num_serious_injury() {
        return people_num_serious_injury;
    }

    public void setPeople_num_serious_injury(String people_num_serious_injury) {
        this.people_num_serious_injury = people_num_serious_injury;
    }

    public String getPeople_num_death() {
        return people_num_death;
    }

    public void setPeople_num_death(String people_num_death) {
        this.people_num_death = people_num_death;
    }

    public String getPolice_num_dispatch() {
        return police_num_dispatch;
    }

    public void setPolice_num_dispatch(String police_num_dispatch) {
        this.police_num_dispatch = police_num_dispatch;
    }

    public String getPolice_car_num_dispatch() {
        return police_car_num_dispatch;
    }

    public void setPolice_car_num_dispatch(String police_car_num_dispatch) {
        this.police_car_num_dispatch = police_car_num_dispatch;
    }

    public String getEconomy_loss() {
        return economy_loss;
    }

    public void setEconomy_loss(String economy_loss) {
        this.economy_loss = economy_loss;
    }

    public String getRetrieve_economy_loss() {
        return retrieve_economy_loss;
    }

    public void setRetrieve_economy_loss(String retrieve_economy_loss) {
        this.retrieve_economy_loss = retrieve_economy_loss;
    }

    public String getFire_put_out_time() {
        return fire_put_out_time;
    }

    public void setFire_put_out_time(String fire_put_out_time) {
        this.fire_put_out_time = fire_put_out_time;
    }

    public String getFire_source_type() {
        return fire_source_type;
    }

    public void setFire_source_type(String fire_source_type) {
        this.fire_source_type = fire_source_type;
    }

    public String getFire_region_type() {
        return fire_region_type;
    }

    public void setFire_region_type(String fire_region_type) {
        this.fire_region_type = fire_region_type;
    }

    public String getFire_cause_first_type() {
        return fire_cause_first_type;
    }

    public void setFire_cause_first_type(String fire_cause_first_type) {
        this.fire_cause_first_type = fire_cause_first_type;
    }

    public String getFire_cause_second_type() {
        return fire_cause_second_type;
    }

    public void setFire_cause_second_type(String fire_cause_second_type) {
        this.fire_cause_second_type = fire_cause_second_type;
    }

    public String getFire_cause_third_type() {
        return fire_cause_third_type;
    }

    public void setFire_cause_third_type(String fire_cause_third_type) {
        this.fire_cause_third_type = fire_cause_third_type;
    }

    public String getFire_area() {
        return fire_area;
    }

    public void setFire_area(String fire_area) {
        this.fire_area = fire_area;
    }

    public String getTraffic_road_level() {
        return traffic_road_level;
    }

    public void setTraffic_road_level(String traffic_road_level) {
        this.traffic_road_level = traffic_road_level;
    }

    public String getTraffic_accident_level() {
        return traffic_accident_level;
    }

    public void setTraffic_accident_level(String traffic_accident_level) {
        this.traffic_accident_level = traffic_accident_level;
    }

    public String getTraffic_vehicle_no() {
        return traffic_vehicle_no;
    }

    public void setTraffic_vehicle_no(String traffic_vehicle_no) {
        this.traffic_vehicle_no = traffic_vehicle_no;
    }

    public String getTraffic_vehicle_type() {
        return traffic_vehicle_type;
    }

    public void setTraffic_vehicle_type(String traffic_vehicle_type) {
        this.traffic_vehicle_type = traffic_vehicle_type;
    }

    public String getTraffic_slave_vehicle_no() {
        return traffic_slave_vehicle_no;
    }

    public void setTraffic_slave_vehicle_no(String traffic_slave_vehicle_no) {
        this.traffic_slave_vehicle_no = traffic_slave_vehicle_no;
    }

    public String getTraffic_slave_vehicle_type() {
        return traffic_slave_vehicle_type;
    }

    public void setTraffic_slave_vehicle_type(String traffic_slave_vehicle_type) {
        this.traffic_slave_vehicle_type = traffic_slave_vehicle_type;
    }

    public String getIs_solve_crown() {
        return is_solve_crown;
    }

    public void setIs_solve_crown(String is_solve_crown) {
        this.is_solve_crown = is_solve_crown;
    }

    public String getIs_solve_public_security() {
        return is_solve_public_security;
    }

    public void setIs_solve_public_security(String is_solve_public_security) {
        this.is_solve_public_security = is_solve_public_security;
    }

    public String getEvent_type() {
        return event_type;
    }

    public void setEvent_type(String event_type) {
        this.event_type = event_type;
    }

    public String getMedia_type() {
        return media_type;
    }

    public void setMedia_type(String media_type) {
        this.media_type = media_type;
    }

    public String getMedia_id() {
        return media_id;
    }

    public void setMedia_id(String media_id) {
        this.media_id = media_id;
    }

    public String getCreate_user() {
        return create_user;
    }

    public void setCreate_user(String create_user) {
        this.create_user = create_user;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUpdate_user() {
        return update_user;
    }

    public void setUpdate_user(String update_user) {
        this.update_user = update_user;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }
}
