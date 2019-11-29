package com.tokisaki.superadmin.service;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.tokisaki.superadmin.common.CommonUtil;
import com.tokisaki.superadmin.domain.User;
import com.tokisaki.superadmin.domain.UserGroup;
import com.tokisaki.superadmin.entity.UseScoreEntity;
import com.tokisaki.superadmin.exception.InvalidInputParamException;
import com.tokisaki.superadmin.exception.NotFoundException;
import com.tokisaki.superadmin.repository.UserGroupRepository;
import com.tokisaki.superadmin.repository.UserRepository;

@Component
public class RankService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserGroupRepository userGroupRepository;

	public RankService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public UseScoreEntity rankForGroupAndLimit() {
		return rankForGroupDetail(false, null);
	}

	public UseScoreEntity rankForGroupAndLimitByGroupId(String groupIdParam) {
		return rankForGroupDetail(true, groupIdParam);
	}

	public UseScoreEntity rankForGroupByTaskId(String taskId) {
	
		return rankForGroupDetail(false, taskId,null);
	}
	public UseScoreEntity rankForGroupByTaskIdAndGroupId(String taskId, String groupId) {
		return rankForGroupDetail(true, taskId,groupId);
	}
	private UseScoreEntity rankForGroupDetail(boolean groupFlg, String taskId, String groupId) {
		UseScoreEntity useScoreEntity = new UseScoreEntity();
		if (!groupFlg) {
			useScoreEntity.setAllList(resetScore(userRepository.selectScoreByTaskId(taskId)));
		}
		User user = CommonUtil.getCurrentUser().get();
		UserGroup usergroup = user.getUserGroup();
		if (groupFlg) {
			if (StringUtils.isEmpty(groupId)) {
				throw new InvalidInputParamException("groupId is null");
			}
			final String groupFinal = groupId;
			usergroup = userGroupRepository.findById(groupFinal)
					.orElseThrow(() -> new NotFoundException("Usergroup", groupFinal));
		}
		if(usergroup!=null) {
			useScoreEntity.setGroupList(resetScore(userRepository.selectScoreByGroupIdAndTaskId(usergroup.getId(),taskId)));
		}
		return useScoreEntity;
	}
	private UseScoreEntity rankForGroupDetail(boolean groupFlg, String groupId) {
		UseScoreEntity useScoreEntity = new UseScoreEntity();
		long currentWeek = 0;
		Instant localDate = Instant.now();
		ZonedDateTime chicago = localDate.atZone(ZoneId.of("Asia/Shanghai"));
		LocalDate localdate = chicago.toLocalDate();
		WeekFields weekFields = WeekFields.of(DayOfWeek.MONDAY, 4);
		currentWeek = localdate.get(weekFields.weekOfWeekBasedYear());
		long currentYear = localdate.getYear();
		long currentMonth = localdate.getMonthValue();
		TemporalField fieldISO = WeekFields.of(Locale.FRANCE).dayOfWeek();
		Date weekStart = Date.from(localdate.with(fieldISO, 1).atStartOfDay(ZoneId.of("Asia/Shanghai")).toInstant());
		Date weekEnd = Date.from(localdate.with(fieldISO, 7).atStartOfDay(ZoneId.of("Asia/Shanghai")).plusDays(1L)
				.minusNanos(1L).toInstant());
		Date monthStart = Date.from(LocalDate.of(localdate.getYear(), localdate.getMonth(), 1)
				.atStartOfDay(ZoneId.of("Asia/Shanghai")).toInstant());
		Date monthEnd = Date.from(LocalDate.of(localdate.getYear(), localdate.getMonth(), 1)
				.atStartOfDay(ZoneId.of("Asia/Shanghai")).plusMonths(1L).minusNanos(1L).toInstant());

		User user = CommonUtil.getCurrentUser().get();
		UserGroup usergroup = user.getUserGroup();
		if (groupFlg) {
			if (StringUtils.isEmpty(groupId)) {
				throw new InvalidInputParamException("groupId is null");
			}
			final String groupFinal = groupId;
			usergroup = userGroupRepository.findById(groupFinal)
					.orElseThrow(() -> new NotFoundException("Usergroup", groupFinal));
		}
		useScoreEntity.setWeekStart(weekStart);
		useScoreEntity.setWeekEnd(weekEnd);
		useScoreEntity.setMonthStart(monthStart);
		useScoreEntity.setMonthEnd(monthEnd);
		if (!groupFlg) {
			useScoreEntity.setAllList(resetScore(userRepository.selectScore()));
			useScoreEntity.setWeekList(resetScore(userRepository.selectScoreAndTimeLimit(weekStart, weekEnd)));
			useScoreEntity.setMonthList(resetScore(userRepository.selectScoreAndTimeLimit(monthStart, monthEnd)));
		}
		if (usergroup != null) {
			groupId = usergroup.getId();
			useScoreEntity.setGroupList(resetScore(userRepository.selectScoreByGroupId(groupId)));
			useScoreEntity.setGroupWeekList(
					resetScore(userRepository.selectScoreByGroupIdAndTimeLimit(groupId, weekStart, weekEnd)));
			useScoreEntity.setGroupMonthList(
					resetScore(userRepository.selectScoreByGroupIdAndTimeLimit(groupId, monthStart, monthEnd)));
		}
		useScoreEntity.setYear(currentYear);
		useScoreEntity.setMonth(currentMonth);
		useScoreEntity.setWeek(currentWeek);
		return useScoreEntity;
	}

	private List<User> resetScore(List<Object[]> list) {
		List<User> lst = new ArrayList<>();
		for (Object[] obj : list) {
			User user = (User) obj[0];
			BigDecimal score = (BigDecimal) obj[1];
			user.setTotalScore(score);
			lst.add(user);
		}
		return lst;
	}



}
