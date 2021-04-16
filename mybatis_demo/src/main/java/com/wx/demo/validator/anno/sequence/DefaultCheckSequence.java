package com.wx.demo.validator.anno.sequence;



import com.wx.demo.validator.anno.group.ExtendedGroup;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

/**
 * 默认校验的分组顺序
 */
@GroupSequence({Default.class, ExtendedGroup.class})
public interface DefaultCheckSequence {
}
