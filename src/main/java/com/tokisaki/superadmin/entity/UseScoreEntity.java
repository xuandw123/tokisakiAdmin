package com.tokisaki.superadmin.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.tokisaki.superadmin.domain.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UseScoreEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<User> allList;
    private List<User> weekList;
    private List<User> monthList;
    private List<User>groupList;
    private List<User> groupWeekList;
    private List<User> groupMonthList;
    long year;
    long month;
    long week;
    String groupId;
    Date weekStart;
    Date weekEnd;
    Date monthStart;
    Date monthEnd;
}
