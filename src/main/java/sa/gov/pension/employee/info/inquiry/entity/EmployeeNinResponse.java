package sa.gov.pension.employee.info.inquiry.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.infinispan.protostream.annotations.ProtoField;
import sa.gov.pension.profile.objects.common.cache.Cacheable;

/**
 * @author Ahmed Anwar
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeNinResponse implements Cacheable {

    @ProtoField(1)
    Long nin;
}
