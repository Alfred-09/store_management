package com.alfred.system.common;

import com.alfred.system.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author Alfred
 * @date 2020/4/28 13:18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActiveUser implements Serializable {
  private User user;
  private List<String>roles;
  private List<String>permissions;
}
