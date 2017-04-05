package com.j2clark.aws.sns;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.ResponseMetadata;
import com.amazonaws.regions.Region;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.AddPermissionRequest;
import com.amazonaws.services.sns.model.AddPermissionResult;
import com.amazonaws.services.sns.model.CheckIfPhoneNumberIsOptedOutRequest;
import com.amazonaws.services.sns.model.CheckIfPhoneNumberIsOptedOutResult;
import com.amazonaws.services.sns.model.ConfirmSubscriptionRequest;
import com.amazonaws.services.sns.model.ConfirmSubscriptionResult;
import com.amazonaws.services.sns.model.CreatePlatformApplicationRequest;
import com.amazonaws.services.sns.model.CreatePlatformApplicationResult;
import com.amazonaws.services.sns.model.CreatePlatformEndpointRequest;
import com.amazonaws.services.sns.model.CreatePlatformEndpointResult;
import com.amazonaws.services.sns.model.CreateTopicRequest;
import com.amazonaws.services.sns.model.CreateTopicResult;
import com.amazonaws.services.sns.model.DeleteEndpointRequest;
import com.amazonaws.services.sns.model.DeleteEndpointResult;
import com.amazonaws.services.sns.model.DeletePlatformApplicationRequest;
import com.amazonaws.services.sns.model.DeletePlatformApplicationResult;
import com.amazonaws.services.sns.model.DeleteTopicRequest;
import com.amazonaws.services.sns.model.DeleteTopicResult;
import com.amazonaws.services.sns.model.GetEndpointAttributesRequest;
import com.amazonaws.services.sns.model.GetEndpointAttributesResult;
import com.amazonaws.services.sns.model.GetPlatformApplicationAttributesRequest;
import com.amazonaws.services.sns.model.GetPlatformApplicationAttributesResult;
import com.amazonaws.services.sns.model.GetSMSAttributesRequest;
import com.amazonaws.services.sns.model.GetSMSAttributesResult;
import com.amazonaws.services.sns.model.GetSubscriptionAttributesRequest;
import com.amazonaws.services.sns.model.GetSubscriptionAttributesResult;
import com.amazonaws.services.sns.model.GetTopicAttributesRequest;
import com.amazonaws.services.sns.model.GetTopicAttributesResult;
import com.amazonaws.services.sns.model.ListEndpointsByPlatformApplicationRequest;
import com.amazonaws.services.sns.model.ListEndpointsByPlatformApplicationResult;
import com.amazonaws.services.sns.model.ListPhoneNumbersOptedOutRequest;
import com.amazonaws.services.sns.model.ListPhoneNumbersOptedOutResult;
import com.amazonaws.services.sns.model.ListPlatformApplicationsRequest;
import com.amazonaws.services.sns.model.ListPlatformApplicationsResult;
import com.amazonaws.services.sns.model.ListSubscriptionsByTopicRequest;
import com.amazonaws.services.sns.model.ListSubscriptionsByTopicResult;
import com.amazonaws.services.sns.model.ListSubscriptionsRequest;
import com.amazonaws.services.sns.model.ListSubscriptionsResult;
import com.amazonaws.services.sns.model.ListTopicsRequest;
import com.amazonaws.services.sns.model.ListTopicsResult;
import com.amazonaws.services.sns.model.OptInPhoneNumberRequest;
import com.amazonaws.services.sns.model.OptInPhoneNumberResult;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.amazonaws.services.sns.model.RemovePermissionRequest;
import com.amazonaws.services.sns.model.RemovePermissionResult;
import com.amazonaws.services.sns.model.SetEndpointAttributesRequest;
import com.amazonaws.services.sns.model.SetEndpointAttributesResult;
import com.amazonaws.services.sns.model.SetPlatformApplicationAttributesRequest;
import com.amazonaws.services.sns.model.SetPlatformApplicationAttributesResult;
import com.amazonaws.services.sns.model.SetSMSAttributesRequest;
import com.amazonaws.services.sns.model.SetSMSAttributesResult;
import com.amazonaws.services.sns.model.SetSubscriptionAttributesRequest;
import com.amazonaws.services.sns.model.SetSubscriptionAttributesResult;
import com.amazonaws.services.sns.model.SetTopicAttributesRequest;
import com.amazonaws.services.sns.model.SetTopicAttributesResult;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.amazonaws.services.sns.model.SubscribeResult;
import com.amazonaws.services.sns.model.UnsubscribeRequest;
import com.amazonaws.services.sns.model.UnsubscribeResult;

import java.util.List;

public class MockAmazonSNS implements AmazonSNS {

    @Override
    public void setEndpoint(String s) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public void setRegion(Region region) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public AddPermissionResult addPermission(AddPermissionRequest addPermissionRequest) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public AddPermissionResult addPermission(String s, String s1, List<String> list,
                                             List<String> list1) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public CheckIfPhoneNumberIsOptedOutResult checkIfPhoneNumberIsOptedOut(
        CheckIfPhoneNumberIsOptedOutRequest checkIfPhoneNumberIsOptedOutRequest) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public ConfirmSubscriptionResult confirmSubscription(
        ConfirmSubscriptionRequest confirmSubscriptionRequest) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public ConfirmSubscriptionResult confirmSubscription(String s, String s1, String s2) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public ConfirmSubscriptionResult confirmSubscription(String s, String s1) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public CreatePlatformApplicationResult createPlatformApplication(
        CreatePlatformApplicationRequest createPlatformApplicationRequest) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public CreatePlatformEndpointResult createPlatformEndpoint(
        CreatePlatformEndpointRequest createPlatformEndpointRequest) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public CreateTopicResult createTopic(CreateTopicRequest createTopicRequest) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public CreateTopicResult createTopic(String s) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public DeleteEndpointResult deleteEndpoint(DeleteEndpointRequest deleteEndpointRequest) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public DeletePlatformApplicationResult deletePlatformApplication(
        DeletePlatformApplicationRequest deletePlatformApplicationRequest) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public DeleteTopicResult deleteTopic(DeleteTopicRequest deleteTopicRequest) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public DeleteTopicResult deleteTopic(String s) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public GetEndpointAttributesResult getEndpointAttributes(
        GetEndpointAttributesRequest getEndpointAttributesRequest) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public GetPlatformApplicationAttributesResult getPlatformApplicationAttributes(
        GetPlatformApplicationAttributesRequest getPlatformApplicationAttributesRequest) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public GetSMSAttributesResult getSMSAttributes(
        GetSMSAttributesRequest getSMSAttributesRequest) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public GetSubscriptionAttributesResult getSubscriptionAttributes(
        GetSubscriptionAttributesRequest getSubscriptionAttributesRequest) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public GetSubscriptionAttributesResult getSubscriptionAttributes(String s) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public GetTopicAttributesResult getTopicAttributes(
        GetTopicAttributesRequest getTopicAttributesRequest) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public GetTopicAttributesResult getTopicAttributes(String s) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public ListEndpointsByPlatformApplicationResult listEndpointsByPlatformApplication(
        ListEndpointsByPlatformApplicationRequest listEndpointsByPlatformApplicationRequest) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public ListPhoneNumbersOptedOutResult listPhoneNumbersOptedOut(
        ListPhoneNumbersOptedOutRequest listPhoneNumbersOptedOutRequest) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public ListPlatformApplicationsResult listPlatformApplications(
        ListPlatformApplicationsRequest listPlatformApplicationsRequest) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public ListPlatformApplicationsResult listPlatformApplications() {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public ListSubscriptionsResult listSubscriptions(
        ListSubscriptionsRequest listSubscriptionsRequest) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public ListSubscriptionsResult listSubscriptions() {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public ListSubscriptionsResult listSubscriptions(String s) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public ListSubscriptionsByTopicResult listSubscriptionsByTopic(
        ListSubscriptionsByTopicRequest listSubscriptionsByTopicRequest) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public ListSubscriptionsByTopicResult listSubscriptionsByTopic(String s) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public ListSubscriptionsByTopicResult listSubscriptionsByTopic(String s, String s1) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public ListTopicsResult listTopics(ListTopicsRequest listTopicsRequest) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public ListTopicsResult listTopics() {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public ListTopicsResult listTopics(String s) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public OptInPhoneNumberResult optInPhoneNumber(
        OptInPhoneNumberRequest optInPhoneNumberRequest) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public PublishResult publish(PublishRequest publishRequest) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public PublishResult publish(String s, String s1) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public PublishResult publish(String s, String s1, String s2) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public RemovePermissionResult removePermission(
        RemovePermissionRequest removePermissionRequest) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public RemovePermissionResult removePermission(String s, String s1) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public SetEndpointAttributesResult setEndpointAttributes(
        SetEndpointAttributesRequest setEndpointAttributesRequest) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public SetPlatformApplicationAttributesResult setPlatformApplicationAttributes(
        SetPlatformApplicationAttributesRequest setPlatformApplicationAttributesRequest) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public SetSMSAttributesResult setSMSAttributes(
        SetSMSAttributesRequest setSMSAttributesRequest) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public SetSubscriptionAttributesResult setSubscriptionAttributes(
        SetSubscriptionAttributesRequest setSubscriptionAttributesRequest) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public SetSubscriptionAttributesResult setSubscriptionAttributes(String s, String s1,
                                                                     String s2) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public SetTopicAttributesResult setTopicAttributes(
        SetTopicAttributesRequest setTopicAttributesRequest) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public SetTopicAttributesResult setTopicAttributes(String s, String s1, String s2) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public SubscribeResult subscribe(SubscribeRequest subscribeRequest) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public SubscribeResult subscribe(String s, String s1, String s2) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public UnsubscribeResult unsubscribe(UnsubscribeRequest unsubscribeRequest) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public UnsubscribeResult unsubscribe(String s) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public void shutdown() {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public ResponseMetadata getCachedResponseMetadata(
        AmazonWebServiceRequest amazonWebServiceRequest) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }
}
