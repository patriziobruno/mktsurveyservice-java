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
package net.dstc.mkts.api;

import java.util.Date;
import java.util.UUID;
import mockit.Expectations;
import mockit.Mocked;
import net.dstc.mkts.data.SurveyDAO;
import net.dstc.mkts.data.SurveyDO;
import net.dstc.mkts.data.SurveyStatus;
import net.dstc.mkts.data.SurveyTargetDO;
import net.dstc.mkts.data.jcr.JcrSurveyDAO;
import net.dstc.mkts.data.jcr.JcrSurveyDO;
import net.dstc.mkts.data.jcr.JcrSurveyTargetDO;
import net.dstc.mkts.data.jpa.JpaSurveyDAO;
import net.dstc.mkts.data.jpa.JpaSurveyDO;
import net.dstc.mkts.data.jpa.JpaSurveyTargetDO;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author eul0860
 */
public class DataConvertersTest {

    public DataConvertersTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Mocked
    private JcrSurveyDAO jcrDao;

    @Mocked
    private JpaSurveyDAO jpaDao;

    /**
     * Test of getConverter method, of class DataConverters.
     */
    @Test
    public void testGetConverter(@Mocked SurveyDAO dao) {
        System.out.println("getConverter");
        DataConverters.Converter result = DataConverters.getConverter(dao);
    }

    @Test
    public void testJcrConverterSurveyDTOtoDO() {
        System.out.println("jcrConverterSurveyDTOtoDO");
        new Expectations() {
            {
                jcrDao.createSurvey();
                returns(new JcrSurveyDO());
                
                jcrDao.createSurveyTarget();
                returns(new JcrSurveyTargetDO());
            }
        };
        DataConverters.Converter jcrConverter = DataConverters.getConverter(jcrDao);

        SurveyTargetDTO target = new SurveyTargetDTO();
        target.setAgeRange(new int[]{30, 45});
        target.setIncomeRange(new int[]{48000, 58000});
        SurveyDTO dto = new SurveyDTO();
        dto.setId("test");
        dto.setTitle("test");
        dto.setStatus(SurveyStatus.NEW);
        dto.setStartDate(new Date());
        dto.setTarget(target);
        SurveyDO DO = jcrConverter.surveyDTOtoDO(dto);
        assertDtoEqualsDo(dto, DO);
    }

    @Test
    public void testJpaConverterSurveyDTOtoDO() {
        System.out.println("jpaConverterSurveyDTOtoDO");
        new Expectations() {
            {
                jpaDao.createSurvey();
                returns(new JpaSurveyDO());
                
                jpaDao.createSurveyTarget();
                returns(new JpaSurveyTargetDO());
            }
        };
        DataConverters.Converter jpaConverter = DataConverters.getConverter(jpaDao);
        
        SurveyTargetDTO target = new SurveyTargetDTO();
        target.setAgeRange(new int[]{30, 45});
        target.setIncomeRange(new int[]{48000, 58000});
        SurveyDTO dto = new SurveyDTO();
        dto.setId("test");
        dto.setTitle("test");
        dto.setStatus(SurveyStatus.NEW);
        dto.setStartDate(new Date());
        dto.setTarget(target);
        SurveyDO DO = jpaConverter.surveyDTOtoDO(dto);
        assertDtoEqualsDo(dto, DO);
    }

    @Test
    public void testJcrConverterSurveyDOtoDTO() {
        System.out.println("jcrConverterSurveyDOtoDTO");
        DataConverters.Converter jcrConverter = DataConverters.getConverter(jcrDao);

        SurveyTargetDO target = new JcrSurveyTargetDO();
        target.setMinAge(30);
        target.setMaxAge(45);
        target.setMinIncome(48000);
        target.setMaxIncome(58000);
        SurveyDO DO = new JcrSurveyDO();
        DO.setId("test");
        DO.setTitle("test");
        DO.setStatus(SurveyStatus.NEW);
        DO.setStartDate(new Date());
        DO.setTarget(target);
        SurveyDTO dto = jcrConverter.surveyDOtoDTO(DO);
        assertDtoEqualsDo(dto, DO);
    }

    @Test
    public void testJpaConverterSurveyDOtoDTO() {
        System.out.println("jpaConverterSurveyDOtoDTO");
        DataConverters.Converter jpaConverter = DataConverters.getConverter(jpaDao);
        

        SurveyTargetDO target = new JpaSurveyTargetDO();
        target.setMinAge(30);
        target.setMaxAge(45);
        target.setMinIncome(48000);
        target.setMaxIncome(58000);
        SurveyDO DO = new JpaSurveyDO();
        DO.setId(UUID.randomUUID().toString());
        DO.setTitle("test");
        DO.setStatus(SurveyStatus.NEW);
        DO.setStartDate(new Date());
        DO.setTarget(target);
        SurveyDTO dto = jpaConverter.surveyDOtoDTO(DO);
        assertDtoEqualsDo(dto, DO);
    }

    private void assertDtoEqualsDo(SurveyDTO dto, SurveyDO DO) {
        assertEquals(dto.getId(), dto.getId());
        assertEquals(dto.getTitle(), dto.getTitle());
        assertEquals(dto.getStartDate(), dto.getStartDate());
        assertEquals(dto.getStatus(), dto.getStatus());

        SurveyTargetDTO dtoTarget = dto.getTarget();
        SurveyTargetDO doTarget = DO.getTarget();
        assertArrayEquals(dtoTarget.getAgeRange(), new int[]{doTarget.getMinAge(), doTarget.getMaxAge()});
        assertArrayEquals(dtoTarget.getIncomeRange(), new int[]{doTarget.getMinIncome(), doTarget.getMaxIncome()});
        assertEquals(dtoTarget.getCountry(), doTarget.getCountry());
        assertEquals(dtoTarget.getGender(), doTarget.getGender());
    }
}
