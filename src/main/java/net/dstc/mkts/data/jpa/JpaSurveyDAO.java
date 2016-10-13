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
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.annotation.ManagedBean;
import javax.annotation.Resource;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import net.dstc.mkts.data.SurveyDO;
import net.dstc.mkts.data.SurveyTargetDO;
import net.dstc.mkts.data.SurveyDAO;

/**
 *
 * @author Patrizio Bruno <desertconsulting@gmail.com>
 */
@Resource
@ManagedBean
@Singleton
public class JpaSurveyDAO implements SurveyDAO {

    private static final Logger LOGGER = Logger.getLogger(JpaSurveyDAO.class.getName());
    private final EntityManagerFactory emf = Persistence.
            createEntityManagerFactory("survey-unit");
    private final EntityManager entityManager = emf.createEntityManager();

    public JpaSurveyDAO() {
//        SurveyDO survey = createSurvey();
//        SurveyTargetDO target = createSurveyTarget();
//        target.setMinAge(18 + (int)(Math.round(Math.random() * 16)));
//        target.setMaxAge(68 + (int)(Math.round(Math.random() * 16)));
//        target.setMinIncome(28000 + (int)(Math.round(Math.random() * 16000)));
//        target.setMaxIncome(48000 + (int)(Math.round(Math.random() * 10000)));
//        target.setGender("F");
//        target.setCountry("ES");
//        survey.setTarget(target);
//        survey.setTitle("test2");
//        survey.setStartDate(new Date());
//        survey.setStatus(SurveyStatus.NEW);
//        add(survey);
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

    @Override
    public Collection<SurveyDO> list(SurveyDO query) {
        try {
            return (Collection<SurveyDO>) entityManager.createQuery(
                    "FROM survey s").
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
}
