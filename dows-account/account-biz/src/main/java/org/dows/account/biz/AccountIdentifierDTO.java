package org.dows.account.biz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author runsix
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountIdentifierDTO {
    private String identifier;
    private String appId;
}
