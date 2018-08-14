package web.jhp7.web.web.rest;

import web.jhp7.web.JhipsterPress07App;

import web.jhp7.web.domain.Notification;
import web.jhp7.web.domain.User;
import web.jhp7.web.repository.NotificationRepository;
import web.jhp7.web.service.NotificationService;
import web.jhp7.web.service.dto.NotificationDTO;
import web.jhp7.web.service.mapper.NotificationMapper;
import web.jhp7.web.web.rest.errors.ExceptionTranslator;
import web.jhp7.web.service.dto.NotificationCriteria;
import web.jhp7.web.service.NotificationQueryService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;


import static web.jhp7.web.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import web.jhp7.web.domain.enumeration.NotificationReason;
/**
 * Test class for the NotificationResource REST controller.
 *
 * @see NotificationResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterPress07App.class)
public class NotificationResourceIntTest {

    private static final Instant DEFAULT_CREATION_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATION_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_NOTIFICATION_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_NOTIFICATION_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final NotificationReason DEFAULT_NOTIFICATION_REASON = NotificationReason.FOLLOWING;
    private static final NotificationReason UPDATED_NOTIFICATION_REASON = NotificationReason.UNFOLLOWING;

    private static final String DEFAULT_NOTIFICATION_TEXT = "AAAAAAAAAA";
    private static final String UPDATED_NOTIFICATION_TEXT = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_DELIVERD = false;
    private static final Boolean UPDATED_IS_DELIVERD = true;

    @Autowired
    private NotificationRepository notificationRepository;


    @Autowired
    private NotificationMapper notificationMapper;
    

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private NotificationQueryService notificationQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restNotificationMockMvc;

    private Notification notification;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final NotificationResource notificationResource = new NotificationResource(notificationService, notificationQueryService);
        this.restNotificationMockMvc = MockMvcBuilders.standaloneSetup(notificationResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Notification createEntity(EntityManager em) {
        Notification notification = new Notification()
            .creationDate(DEFAULT_CREATION_DATE)
            .notificationDate(DEFAULT_NOTIFICATION_DATE)
            .notificationReason(DEFAULT_NOTIFICATION_REASON)
            .notificationText(DEFAULT_NOTIFICATION_TEXT)
            .isDeliverd(DEFAULT_IS_DELIVERD);
        // Add required entity
        User user = UserResourceIntTest.createEntity(em);
        em.persist(user);
        em.flush();
        notification.setUser(user);
        return notification;
    }

    @Before
    public void initTest() {
        notification = createEntity(em);
    }

    @Test
    @Transactional
    public void createNotification() throws Exception {
        int databaseSizeBeforeCreate = notificationRepository.findAll().size();

        // Create the Notification
        NotificationDTO notificationDTO = notificationMapper.toDto(notification);
        restNotificationMockMvc.perform(post("/api/notifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(notificationDTO)))
            .andExpect(status().isCreated());

        // Validate the Notification in the database
        List<Notification> notificationList = notificationRepository.findAll();
        assertThat(notificationList).hasSize(databaseSizeBeforeCreate + 1);
        Notification testNotification = notificationList.get(notificationList.size() - 1);
        assertThat(testNotification.getCreationDate()).isEqualTo(DEFAULT_CREATION_DATE);
        assertThat(testNotification.getNotificationDate()).isEqualTo(DEFAULT_NOTIFICATION_DATE);
        assertThat(testNotification.getNotificationReason()).isEqualTo(DEFAULT_NOTIFICATION_REASON);
        assertThat(testNotification.getNotificationText()).isEqualTo(DEFAULT_NOTIFICATION_TEXT);
        assertThat(testNotification.isIsDeliverd()).isEqualTo(DEFAULT_IS_DELIVERD);
    }

    @Test
    @Transactional
    public void createNotificationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = notificationRepository.findAll().size();

        // Create the Notification with an existing ID
        notification.setId(1L);
        NotificationDTO notificationDTO = notificationMapper.toDto(notification);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNotificationMockMvc.perform(post("/api/notifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(notificationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Notification in the database
        List<Notification> notificationList = notificationRepository.findAll();
        assertThat(notificationList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkCreationDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = notificationRepository.findAll().size();
        // set the field null
        notification.setCreationDate(null);

        // Create the Notification, which fails.
        NotificationDTO notificationDTO = notificationMapper.toDto(notification);

        restNotificationMockMvc.perform(post("/api/notifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(notificationDTO)))
            .andExpect(status().isBadRequest());

        List<Notification> notificationList = notificationRepository.findAll();
        assertThat(notificationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNotificationReasonIsRequired() throws Exception {
        int databaseSizeBeforeTest = notificationRepository.findAll().size();
        // set the field null
        notification.setNotificationReason(null);

        // Create the Notification, which fails.
        NotificationDTO notificationDTO = notificationMapper.toDto(notification);

        restNotificationMockMvc.perform(post("/api/notifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(notificationDTO)))
            .andExpect(status().isBadRequest());

        List<Notification> notificationList = notificationRepository.findAll();
        assertThat(notificationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllNotifications() throws Exception {
        // Initialize the database
        notificationRepository.saveAndFlush(notification);

        // Get all the notificationList
        restNotificationMockMvc.perform(get("/api/notifications?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(notification.getId().intValue())))
            .andExpect(jsonPath("$.[*].creationDate").value(hasItem(DEFAULT_CREATION_DATE.toString())))
            .andExpect(jsonPath("$.[*].notificationDate").value(hasItem(DEFAULT_NOTIFICATION_DATE.toString())))
            .andExpect(jsonPath("$.[*].notificationReason").value(hasItem(DEFAULT_NOTIFICATION_REASON.toString())))
            .andExpect(jsonPath("$.[*].notificationText").value(hasItem(DEFAULT_NOTIFICATION_TEXT.toString())))
            .andExpect(jsonPath("$.[*].isDeliverd").value(hasItem(DEFAULT_IS_DELIVERD.booleanValue())));
    }
    

    @Test
    @Transactional
    public void getNotification() throws Exception {
        // Initialize the database
        notificationRepository.saveAndFlush(notification);

        // Get the notification
        restNotificationMockMvc.perform(get("/api/notifications/{id}", notification.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(notification.getId().intValue()))
            .andExpect(jsonPath("$.creationDate").value(DEFAULT_CREATION_DATE.toString()))
            .andExpect(jsonPath("$.notificationDate").value(DEFAULT_NOTIFICATION_DATE.toString()))
            .andExpect(jsonPath("$.notificationReason").value(DEFAULT_NOTIFICATION_REASON.toString()))
            .andExpect(jsonPath("$.notificationText").value(DEFAULT_NOTIFICATION_TEXT.toString()))
            .andExpect(jsonPath("$.isDeliverd").value(DEFAULT_IS_DELIVERD.booleanValue()));
    }

    @Test
    @Transactional
    public void getAllNotificationsByCreationDateIsEqualToSomething() throws Exception {
        // Initialize the database
        notificationRepository.saveAndFlush(notification);

        // Get all the notificationList where creationDate equals to DEFAULT_CREATION_DATE
        defaultNotificationShouldBeFound("creationDate.equals=" + DEFAULT_CREATION_DATE);

        // Get all the notificationList where creationDate equals to UPDATED_CREATION_DATE
        defaultNotificationShouldNotBeFound("creationDate.equals=" + UPDATED_CREATION_DATE);
    }

    @Test
    @Transactional
    public void getAllNotificationsByCreationDateIsInShouldWork() throws Exception {
        // Initialize the database
        notificationRepository.saveAndFlush(notification);

        // Get all the notificationList where creationDate in DEFAULT_CREATION_DATE or UPDATED_CREATION_DATE
        defaultNotificationShouldBeFound("creationDate.in=" + DEFAULT_CREATION_DATE + "," + UPDATED_CREATION_DATE);

        // Get all the notificationList where creationDate equals to UPDATED_CREATION_DATE
        defaultNotificationShouldNotBeFound("creationDate.in=" + UPDATED_CREATION_DATE);
    }

    @Test
    @Transactional
    public void getAllNotificationsByCreationDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        notificationRepository.saveAndFlush(notification);

        // Get all the notificationList where creationDate is not null
        defaultNotificationShouldBeFound("creationDate.specified=true");

        // Get all the notificationList where creationDate is null
        defaultNotificationShouldNotBeFound("creationDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllNotificationsByNotificationDateIsEqualToSomething() throws Exception {
        // Initialize the database
        notificationRepository.saveAndFlush(notification);

        // Get all the notificationList where notificationDate equals to DEFAULT_NOTIFICATION_DATE
        defaultNotificationShouldBeFound("notificationDate.equals=" + DEFAULT_NOTIFICATION_DATE);

        // Get all the notificationList where notificationDate equals to UPDATED_NOTIFICATION_DATE
        defaultNotificationShouldNotBeFound("notificationDate.equals=" + UPDATED_NOTIFICATION_DATE);
    }

    @Test
    @Transactional
    public void getAllNotificationsByNotificationDateIsInShouldWork() throws Exception {
        // Initialize the database
        notificationRepository.saveAndFlush(notification);

        // Get all the notificationList where notificationDate in DEFAULT_NOTIFICATION_DATE or UPDATED_NOTIFICATION_DATE
        defaultNotificationShouldBeFound("notificationDate.in=" + DEFAULT_NOTIFICATION_DATE + "," + UPDATED_NOTIFICATION_DATE);

        // Get all the notificationList where notificationDate equals to UPDATED_NOTIFICATION_DATE
        defaultNotificationShouldNotBeFound("notificationDate.in=" + UPDATED_NOTIFICATION_DATE);
    }

    @Test
    @Transactional
    public void getAllNotificationsByNotificationDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        notificationRepository.saveAndFlush(notification);

        // Get all the notificationList where notificationDate is not null
        defaultNotificationShouldBeFound("notificationDate.specified=true");

        // Get all the notificationList where notificationDate is null
        defaultNotificationShouldNotBeFound("notificationDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllNotificationsByNotificationReasonIsEqualToSomething() throws Exception {
        // Initialize the database
        notificationRepository.saveAndFlush(notification);

        // Get all the notificationList where notificationReason equals to DEFAULT_NOTIFICATION_REASON
        defaultNotificationShouldBeFound("notificationReason.equals=" + DEFAULT_NOTIFICATION_REASON);

        // Get all the notificationList where notificationReason equals to UPDATED_NOTIFICATION_REASON
        defaultNotificationShouldNotBeFound("notificationReason.equals=" + UPDATED_NOTIFICATION_REASON);
    }

    @Test
    @Transactional
    public void getAllNotificationsByNotificationReasonIsInShouldWork() throws Exception {
        // Initialize the database
        notificationRepository.saveAndFlush(notification);

        // Get all the notificationList where notificationReason in DEFAULT_NOTIFICATION_REASON or UPDATED_NOTIFICATION_REASON
        defaultNotificationShouldBeFound("notificationReason.in=" + DEFAULT_NOTIFICATION_REASON + "," + UPDATED_NOTIFICATION_REASON);

        // Get all the notificationList where notificationReason equals to UPDATED_NOTIFICATION_REASON
        defaultNotificationShouldNotBeFound("notificationReason.in=" + UPDATED_NOTIFICATION_REASON);
    }

    @Test
    @Transactional
    public void getAllNotificationsByNotificationReasonIsNullOrNotNull() throws Exception {
        // Initialize the database
        notificationRepository.saveAndFlush(notification);

        // Get all the notificationList where notificationReason is not null
        defaultNotificationShouldBeFound("notificationReason.specified=true");

        // Get all the notificationList where notificationReason is null
        defaultNotificationShouldNotBeFound("notificationReason.specified=false");
    }

    @Test
    @Transactional
    public void getAllNotificationsByNotificationTextIsEqualToSomething() throws Exception {
        // Initialize the database
        notificationRepository.saveAndFlush(notification);

        // Get all the notificationList where notificationText equals to DEFAULT_NOTIFICATION_TEXT
        defaultNotificationShouldBeFound("notificationText.equals=" + DEFAULT_NOTIFICATION_TEXT);

        // Get all the notificationList where notificationText equals to UPDATED_NOTIFICATION_TEXT
        defaultNotificationShouldNotBeFound("notificationText.equals=" + UPDATED_NOTIFICATION_TEXT);
    }

    @Test
    @Transactional
    public void getAllNotificationsByNotificationTextIsInShouldWork() throws Exception {
        // Initialize the database
        notificationRepository.saveAndFlush(notification);

        // Get all the notificationList where notificationText in DEFAULT_NOTIFICATION_TEXT or UPDATED_NOTIFICATION_TEXT
        defaultNotificationShouldBeFound("notificationText.in=" + DEFAULT_NOTIFICATION_TEXT + "," + UPDATED_NOTIFICATION_TEXT);

        // Get all the notificationList where notificationText equals to UPDATED_NOTIFICATION_TEXT
        defaultNotificationShouldNotBeFound("notificationText.in=" + UPDATED_NOTIFICATION_TEXT);
    }

    @Test
    @Transactional
    public void getAllNotificationsByNotificationTextIsNullOrNotNull() throws Exception {
        // Initialize the database
        notificationRepository.saveAndFlush(notification);

        // Get all the notificationList where notificationText is not null
        defaultNotificationShouldBeFound("notificationText.specified=true");

        // Get all the notificationList where notificationText is null
        defaultNotificationShouldNotBeFound("notificationText.specified=false");
    }

    @Test
    @Transactional
    public void getAllNotificationsByIsDeliverdIsEqualToSomething() throws Exception {
        // Initialize the database
        notificationRepository.saveAndFlush(notification);

        // Get all the notificationList where isDeliverd equals to DEFAULT_IS_DELIVERD
        defaultNotificationShouldBeFound("isDeliverd.equals=" + DEFAULT_IS_DELIVERD);

        // Get all the notificationList where isDeliverd equals to UPDATED_IS_DELIVERD
        defaultNotificationShouldNotBeFound("isDeliverd.equals=" + UPDATED_IS_DELIVERD);
    }

    @Test
    @Transactional
    public void getAllNotificationsByIsDeliverdIsInShouldWork() throws Exception {
        // Initialize the database
        notificationRepository.saveAndFlush(notification);

        // Get all the notificationList where isDeliverd in DEFAULT_IS_DELIVERD or UPDATED_IS_DELIVERD
        defaultNotificationShouldBeFound("isDeliverd.in=" + DEFAULT_IS_DELIVERD + "," + UPDATED_IS_DELIVERD);

        // Get all the notificationList where isDeliverd equals to UPDATED_IS_DELIVERD
        defaultNotificationShouldNotBeFound("isDeliverd.in=" + UPDATED_IS_DELIVERD);
    }

    @Test
    @Transactional
    public void getAllNotificationsByIsDeliverdIsNullOrNotNull() throws Exception {
        // Initialize the database
        notificationRepository.saveAndFlush(notification);

        // Get all the notificationList where isDeliverd is not null
        defaultNotificationShouldBeFound("isDeliverd.specified=true");

        // Get all the notificationList where isDeliverd is null
        defaultNotificationShouldNotBeFound("isDeliverd.specified=false");
    }

    @Test
    @Transactional
    public void getAllNotificationsByUserIsEqualToSomething() throws Exception {
        // Initialize the database
        User user = UserResourceIntTest.createEntity(em);
        em.persist(user);
        em.flush();
        notification.setUser(user);
        notificationRepository.saveAndFlush(notification);
        Long userId = user.getId();

        // Get all the notificationList where user equals to userId
        defaultNotificationShouldBeFound("userId.equals=" + userId);

        // Get all the notificationList where user equals to userId + 1
        defaultNotificationShouldNotBeFound("userId.equals=" + (userId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultNotificationShouldBeFound(String filter) throws Exception {
        restNotificationMockMvc.perform(get("/api/notifications?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(notification.getId().intValue())))
            .andExpect(jsonPath("$.[*].creationDate").value(hasItem(DEFAULT_CREATION_DATE.toString())))
            .andExpect(jsonPath("$.[*].notificationDate").value(hasItem(DEFAULT_NOTIFICATION_DATE.toString())))
            .andExpect(jsonPath("$.[*].notificationReason").value(hasItem(DEFAULT_NOTIFICATION_REASON.toString())))
            .andExpect(jsonPath("$.[*].notificationText").value(hasItem(DEFAULT_NOTIFICATION_TEXT.toString())))
            .andExpect(jsonPath("$.[*].isDeliverd").value(hasItem(DEFAULT_IS_DELIVERD.booleanValue())));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultNotificationShouldNotBeFound(String filter) throws Exception {
        restNotificationMockMvc.perform(get("/api/notifications?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    @Transactional
    public void getNonExistingNotification() throws Exception {
        // Get the notification
        restNotificationMockMvc.perform(get("/api/notifications/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNotification() throws Exception {
        // Initialize the database
        notificationRepository.saveAndFlush(notification);

        int databaseSizeBeforeUpdate = notificationRepository.findAll().size();

        // Update the notification
        Notification updatedNotification = notificationRepository.findById(notification.getId()).get();
        // Disconnect from session so that the updates on updatedNotification are not directly saved in db
        em.detach(updatedNotification);
        updatedNotification
            .creationDate(UPDATED_CREATION_DATE)
            .notificationDate(UPDATED_NOTIFICATION_DATE)
            .notificationReason(UPDATED_NOTIFICATION_REASON)
            .notificationText(UPDATED_NOTIFICATION_TEXT)
            .isDeliverd(UPDATED_IS_DELIVERD);
        NotificationDTO notificationDTO = notificationMapper.toDto(updatedNotification);

        restNotificationMockMvc.perform(put("/api/notifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(notificationDTO)))
            .andExpect(status().isOk());

        // Validate the Notification in the database
        List<Notification> notificationList = notificationRepository.findAll();
        assertThat(notificationList).hasSize(databaseSizeBeforeUpdate);
        Notification testNotification = notificationList.get(notificationList.size() - 1);
        assertThat(testNotification.getCreationDate()).isEqualTo(UPDATED_CREATION_DATE);
        assertThat(testNotification.getNotificationDate()).isEqualTo(UPDATED_NOTIFICATION_DATE);
        assertThat(testNotification.getNotificationReason()).isEqualTo(UPDATED_NOTIFICATION_REASON);
        assertThat(testNotification.getNotificationText()).isEqualTo(UPDATED_NOTIFICATION_TEXT);
        assertThat(testNotification.isIsDeliverd()).isEqualTo(UPDATED_IS_DELIVERD);
    }

    @Test
    @Transactional
    public void updateNonExistingNotification() throws Exception {
        int databaseSizeBeforeUpdate = notificationRepository.findAll().size();

        // Create the Notification
        NotificationDTO notificationDTO = notificationMapper.toDto(notification);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restNotificationMockMvc.perform(put("/api/notifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(notificationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Notification in the database
        List<Notification> notificationList = notificationRepository.findAll();
        assertThat(notificationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNotification() throws Exception {
        // Initialize the database
        notificationRepository.saveAndFlush(notification);

        int databaseSizeBeforeDelete = notificationRepository.findAll().size();

        // Get the notification
        restNotificationMockMvc.perform(delete("/api/notifications/{id}", notification.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Notification> notificationList = notificationRepository.findAll();
        assertThat(notificationList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Notification.class);
        Notification notification1 = new Notification();
        notification1.setId(1L);
        Notification notification2 = new Notification();
        notification2.setId(notification1.getId());
        assertThat(notification1).isEqualTo(notification2);
        notification2.setId(2L);
        assertThat(notification1).isNotEqualTo(notification2);
        notification1.setId(null);
        assertThat(notification1).isNotEqualTo(notification2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NotificationDTO.class);
        NotificationDTO notificationDTO1 = new NotificationDTO();
        notificationDTO1.setId(1L);
        NotificationDTO notificationDTO2 = new NotificationDTO();
        assertThat(notificationDTO1).isNotEqualTo(notificationDTO2);
        notificationDTO2.setId(notificationDTO1.getId());
        assertThat(notificationDTO1).isEqualTo(notificationDTO2);
        notificationDTO2.setId(2L);
        assertThat(notificationDTO1).isNotEqualTo(notificationDTO2);
        notificationDTO1.setId(null);
        assertThat(notificationDTO1).isNotEqualTo(notificationDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(notificationMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(notificationMapper.fromId(null)).isNull();
    }
}
