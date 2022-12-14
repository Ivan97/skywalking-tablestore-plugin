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

package org.apache.skywalking.apm.plugin.tablestore;

import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.matcher.ElementMatcher;
import org.apache.skywalking.apm.agent.core.plugin.interceptor.ConstructorInterceptPoint;
import org.apache.skywalking.apm.agent.core.plugin.interceptor.InstanceMethodsInterceptPoint;
import org.apache.skywalking.apm.agent.core.plugin.interceptor.enhance.ClassInstanceMethodsEnhancePluginDefine;
import org.apache.skywalking.apm.agent.core.plugin.match.ClassMatch;
import org.apache.skywalking.apm.agent.core.plugin.match.NameMatch;

import static net.bytebuddy.matcher.ElementMatchers.named;

/**
 * @author 龙也
 * @date 2022/10/13 7:41 PM
 */
public class TablestoreInstrumentation extends ClassInstanceMethodsEnhancePluginDefine {

  private static final String ENHANCE_CLASS = "com.alicloud.openservices.tablestore.SyncClient";
  private static final String INTERCEPT_CLASS =
      "org.apache.skywalking.apm.plugin.tablestore.TablestoreInterceptor";

  @Override
  protected ClassMatch enhanceClass() {
    return NameMatch.byName(ENHANCE_CLASS);
  }

  @Override
  public ConstructorInterceptPoint[] getConstructorsInterceptPoints() {
    return new ConstructorInterceptPoint[0];
  }

  @Override
  public InstanceMethodsInterceptPoint[] getInstanceMethodsInterceptPoints() {

    return new InstanceMethodsInterceptPoint[] {
      new InstanceMethodsInterceptPoint() {

        @Override
        public ElementMatcher<MethodDescription> getMethodsMatcher() {
          return named("setExtraHeaders")
              .or(named("getEndpoint"))
              .or(named("getInstanceName"))
              .or(named("createTable"))
              .or(named("listTable"))
              .or(named("describeTable"))
              .or(named("deleteTable"))
              .or(named("updateTable"))
              .or(named("createIndex"))
              .or(named("deleteIndex"))
              .or(named("addDefinedColumn"))
              .or(named("deleteDefinedColumn"))
              .or(named("getRow"))
              .or(named("putRow"))
              .or(named("updateRow"))
              .or(named("deleteRow"))
              .or(named("batchGetRow"))
              .or(named("batchWriteRow"))
              .or(named("bulkImport"))
              .or(named("getRange"))
              .or(named("bulkExport"))
              .or(named("computeSplitsBySize"))
              .or(named("createRangeIterator"))
              .or(named("createBulkExportIterator"))
              .or(named("createWideColumnIterator"))
              .or(named("listStream"))
              .or(named("describeStream"))
              .or(named("getShardIterator"))
              .or(named("getStreamRecord"))
              .or(named("createSearchIndex"))
              .or(named("updateSearchIndex"))
              .or(named("listSearchIndex"))
              .or(named("deleteSearchIndex"))
              .or(named("describeSearchIndex"))
              .or(named("computeSplits"))
              .or(named("parallelScan"))
              .or(named("createParallelScanIterator"))
              .or(named("search"))
              .or(named("startLocalTransaction"))
              .or(named("commitTransaction"))
              .or(named("abortTransaction"))
              .or(named("createDeliveryTask"))
              .or(named("deleteDeliveryTask"))
              .or(named("describeDeliveryTask"))
              .or(named("listDeliveryTask"))
              .or(named("sqlQuery"));
        }

        @Override
        public String getMethodsInterceptor() {
          return INTERCEPT_CLASS;
        }

        @Override
        public boolean isOverrideArgs() {
          return false;
        }
      }
    };
  }
}
