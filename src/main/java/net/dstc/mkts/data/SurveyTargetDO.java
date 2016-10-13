/* 
 * Copyright 2016 eul0860.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.dstc.mkts.data;

import org.apache.jackrabbit.ocm.mapper.impl.annotation.Node;

/**
 *
 * @author Patrizio Bruno <desertconsulting@gmail.com>
 */
@Node
public interface SurveyTargetDO {

    String getCountry();

    String getGender();

    int getMaxAge();

    int getMaxIncome();

    int getMinAge();

    int getMinIncome();

    void setCountry(String country);

    void setGender(String gender);

    void setMaxAge(int maxAge);

    void setMaxIncome(int maxIncome);

    void setMinAge(int minAge);

    void setMinIncome(int minIncome);
}
