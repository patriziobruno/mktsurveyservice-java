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

import java.util.Collection;

/**
 *
 * @author Patrizio Bruno <desertconsulting@gmail.com>
 */
public interface SurveyDAO {

    /**
     * Save a new survey into the storage
     * @param survey survey to be saved
     */
    void add(SurveyDO survey);

    /**
     * Delete a survey from the storage
     * @param id id of the survey to be deleted
     */
    void delete(String id);

    /**
     * Retrieve a survey from the storage
     * @param id id of the storage to be retrieved
     * @return 
     */
    SurveyDO get(String id);

    /**
     * Find surveys in the storage
     * @param query find every survey matching all the fields set into query
     * @return a collection of surveys
     */
    Collection<SurveyDO> list(SurveyDO query);

    /**
     * Modify a survey alredy saved into the storage
     * @param survey survey to be updated, the id must match an existing survey
     */
    void update(SurveyDO survey);
    
    /**
     * Create a new instance of an object implementing {@link SurveyDO}
     * @return a new instance of an object implementing {@link SurveyDO}
     */
    SurveyDO createSurvey();
    
    
    /**
     * Create a new instance of an object implementing {@link SurveyTargetDO}
     * @return a new instance of an object implementing {@link SurveyTargetDO}
     */
    SurveyTargetDO createSurveyTarget();
    
    /**
     * Shut down the storage backend
     */
    public void shutdown();
}
