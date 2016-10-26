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
package net.dstc.mkts.data.jpa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.annotation.ManagedBean;
import javax.annotation.Resource;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import net.dstc.mkts.data.SurveyDO;
import net.dstc.mkts.data.SurveyTargetDO;
import net.dstc.mkts.data.SurveyDAO;
import net.dstc.mkts.data.SurveyStatus;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Patrizio Bruno <desertconsulting@gmail.com>
 */
@Resource
@ManagedBean
@Singleton
public class JpaSurveyDAO implements SurveyDAO {

    private static final Logger LOGGER = Logger.getLogger(JpaSurveyDAO.class.
            getName());
    private final EntityManagerFactory emf = Persistence.
            createEntityManagerFactory("survey-unit");
    private final EntityManager entityManager = emf.createEntityManager();

    public JpaSurveyDAO() {
    }

    @Override
    public void add(SurveyDO survey) {
        entityManager.getTransaction().begin();
        entityManager.persist(survey);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(String id) {
        SurveyDO survey = createSurvey();
        survey.setId(id);
        entityManager.getTransaction().begin();
        entityManager.remove(survey);
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(SurveyDO survey) {
        entityManager.getTransaction().begin();
        entityManager.persist(survey);
        entityManager.getTransaction().commit();
    }

    @Override
    public SurveyDO get(String id) {
        return entityManager.find(JpaSurveyDO.class, id);
    }

    private Query buildQuery(SurveyDO query) {
        String queryString = "FROM survey s";
        String whereClause = StringUtils.EMPTY;
        Map<String, Object> parameters = new Hashtable<>(20);

        if (query != null) {
            String title = query.getTitle();
            SurveyStatus status = query.getStatus();
            Date startDate = query.getStartDate();
            SurveyTargetDO target = query.getTarget();
            if (!StringUtils.isBlank(title)) {
                whereClause = "title=:title";
                parameters.put("title", title);
            }

            if (status != null) {
                whereClause += !StringUtils.isEmpty(whereClause) ? " AND "
                        : StringUtils.EMPTY;
                whereClause += "status=:status";
                parameters.put("status", status);
            }

            if (startDate != null) {
                whereClause += !StringUtils.isEmpty(whereClause) ? " AND "
                        : StringUtils.EMPTY;
                whereClause += "startDate=:startDate";
                parameters.put("startDate", startDate);
            }

            if (target != null) {
                int minAge = target.getMinAge();
                int maxAge = target.getMaxAge();
                int minIncome = target.getMinIncome();
                int maxIncome = target.getMaxIncome();
                String gender = target.getGender();
                String country = target.getCountry();

                if (minAge > 0) {
                    whereClause += !StringUtils.isEmpty(whereClause) ? " AND "
                            : StringUtils.EMPTY;
                    whereClause += "target.minAge=:minAge";
                    parameters.put("target.minAge", minAge);
                }

                if (maxAge > 0) {
                    whereClause += !StringUtils.isEmpty(whereClause) ? " AND "
                            : StringUtils.EMPTY;
                    whereClause += "target.maxAge=:maxAge";
                    parameters.put("target.maxAge", maxAge);
                }

                if (minIncome > 0) {
                    whereClause += !StringUtils.isEmpty(whereClause) ? " AND "
                            : StringUtils.EMPTY;
                    whereClause += "target.minIncome=:minIncome";
                    parameters.put("target.minIncome", minIncome);
                }

                if (maxIncome > 0) {
                    whereClause += !StringUtils.isEmpty(whereClause) ? " AND "
                            : StringUtils.EMPTY;
                    whereClause += "target.maxIncome=:maxIncome";
                    parameters.put("target.maxIncome", maxIncome);
                }
                
                if(!StringUtils.isBlank(gender)) {
                    whereClause += !StringUtils.isEmpty(whereClause) ? " AND "
                            : StringUtils.EMPTY;
                    whereClause += "target.gender=:gender";
                    parameters.put("gender", gender);
                }
                                
                if(!StringUtils.isBlank(country)) {
                    whereClause += !StringUtils.isEmpty(whereClause) ? " AND "
                            : StringUtils.EMPTY;
                    whereClause += "target.country=:country";
                    parameters.put("target.country", country);
                }
            }

            if (!StringUtils.isEmpty(whereClause)) {
                queryString += " WHERE " + whereClause;
            }
        }

        Query jpaQuery = entityManager.createQuery(
                queryString);

        parameters.forEach((String parmName, Object parmValue) -> {
            jpaQuery.setParameter(parmName, parmValue);
        });
        
        return jpaQuery;
    }

    @Override
    public Collection<SurveyDO> list(SurveyDO query) {
        try {
            return (Collection<SurveyDO>) buildQuery(query).
                    getResultList().stream().collect(Collectors.toList());
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
            return new ArrayList<>();
        }
    }

    @Override
    public SurveyDO createSurvey() {
        return new JpaSurveyDO();
    }

    @Override
    public SurveyTargetDO createSurveyTarget() {
        return new JpaSurveyTargetDO();
    }
//
//    @PreDestroy
//    private void shutdown() {
//        entityManager.close();
//    }
}
