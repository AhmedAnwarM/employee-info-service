package sa.gov.pension.employee.info.config.cache;

import org.infinispan.protostream.SerializationContextInitializer;
import org.infinispan.protostream.annotations.AutoProtoSchemaBuilder;
import sa.gov.pension.employee.info.inquiry.entity.EmployeeNinResponse;

@AutoProtoSchemaBuilder(includeClasses = EmployeeNinResponse.class, schemaPackageName = "profile.employee.info")
interface CacheContextInitializer extends SerializationContextInitializer {

}
