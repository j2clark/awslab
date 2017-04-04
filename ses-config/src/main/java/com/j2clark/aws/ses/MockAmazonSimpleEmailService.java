package com.j2clark.aws.ses;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.ResponseMetadata;
import com.amazonaws.regions.Region;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.CloneReceiptRuleSetRequest;
import com.amazonaws.services.simpleemail.model.CloneReceiptRuleSetResult;
import com.amazonaws.services.simpleemail.model.CreateConfigurationSetEventDestinationRequest;
import com.amazonaws.services.simpleemail.model.CreateConfigurationSetEventDestinationResult;
import com.amazonaws.services.simpleemail.model.CreateConfigurationSetRequest;
import com.amazonaws.services.simpleemail.model.CreateConfigurationSetResult;
import com.amazonaws.services.simpleemail.model.CreateReceiptFilterRequest;
import com.amazonaws.services.simpleemail.model.CreateReceiptFilterResult;
import com.amazonaws.services.simpleemail.model.CreateReceiptRuleRequest;
import com.amazonaws.services.simpleemail.model.CreateReceiptRuleResult;
import com.amazonaws.services.simpleemail.model.CreateReceiptRuleSetRequest;
import com.amazonaws.services.simpleemail.model.CreateReceiptRuleSetResult;
import com.amazonaws.services.simpleemail.model.DeleteConfigurationSetEventDestinationRequest;
import com.amazonaws.services.simpleemail.model.DeleteConfigurationSetEventDestinationResult;
import com.amazonaws.services.simpleemail.model.DeleteConfigurationSetRequest;
import com.amazonaws.services.simpleemail.model.DeleteConfigurationSetResult;
import com.amazonaws.services.simpleemail.model.DeleteIdentityPolicyRequest;
import com.amazonaws.services.simpleemail.model.DeleteIdentityPolicyResult;
import com.amazonaws.services.simpleemail.model.DeleteIdentityRequest;
import com.amazonaws.services.simpleemail.model.DeleteIdentityResult;
import com.amazonaws.services.simpleemail.model.DeleteReceiptFilterRequest;
import com.amazonaws.services.simpleemail.model.DeleteReceiptFilterResult;
import com.amazonaws.services.simpleemail.model.DeleteReceiptRuleRequest;
import com.amazonaws.services.simpleemail.model.DeleteReceiptRuleResult;
import com.amazonaws.services.simpleemail.model.DeleteReceiptRuleSetRequest;
import com.amazonaws.services.simpleemail.model.DeleteReceiptRuleSetResult;
import com.amazonaws.services.simpleemail.model.DeleteVerifiedEmailAddressRequest;
import com.amazonaws.services.simpleemail.model.DeleteVerifiedEmailAddressResult;
import com.amazonaws.services.simpleemail.model.DescribeActiveReceiptRuleSetRequest;
import com.amazonaws.services.simpleemail.model.DescribeActiveReceiptRuleSetResult;
import com.amazonaws.services.simpleemail.model.DescribeConfigurationSetRequest;
import com.amazonaws.services.simpleemail.model.DescribeConfigurationSetResult;
import com.amazonaws.services.simpleemail.model.DescribeReceiptRuleRequest;
import com.amazonaws.services.simpleemail.model.DescribeReceiptRuleResult;
import com.amazonaws.services.simpleemail.model.DescribeReceiptRuleSetRequest;
import com.amazonaws.services.simpleemail.model.DescribeReceiptRuleSetResult;
import com.amazonaws.services.simpleemail.model.GetIdentityDkimAttributesRequest;
import com.amazonaws.services.simpleemail.model.GetIdentityDkimAttributesResult;
import com.amazonaws.services.simpleemail.model.GetIdentityMailFromDomainAttributesRequest;
import com.amazonaws.services.simpleemail.model.GetIdentityMailFromDomainAttributesResult;
import com.amazonaws.services.simpleemail.model.GetIdentityNotificationAttributesRequest;
import com.amazonaws.services.simpleemail.model.GetIdentityNotificationAttributesResult;
import com.amazonaws.services.simpleemail.model.GetIdentityPoliciesRequest;
import com.amazonaws.services.simpleemail.model.GetIdentityPoliciesResult;
import com.amazonaws.services.simpleemail.model.GetIdentityVerificationAttributesRequest;
import com.amazonaws.services.simpleemail.model.GetIdentityVerificationAttributesResult;
import com.amazonaws.services.simpleemail.model.GetSendQuotaRequest;
import com.amazonaws.services.simpleemail.model.GetSendQuotaResult;
import com.amazonaws.services.simpleemail.model.GetSendStatisticsRequest;
import com.amazonaws.services.simpleemail.model.GetSendStatisticsResult;
import com.amazonaws.services.simpleemail.model.ListConfigurationSetsRequest;
import com.amazonaws.services.simpleemail.model.ListConfigurationSetsResult;
import com.amazonaws.services.simpleemail.model.ListIdentitiesRequest;
import com.amazonaws.services.simpleemail.model.ListIdentitiesResult;
import com.amazonaws.services.simpleemail.model.ListIdentityPoliciesRequest;
import com.amazonaws.services.simpleemail.model.ListIdentityPoliciesResult;
import com.amazonaws.services.simpleemail.model.ListReceiptFiltersRequest;
import com.amazonaws.services.simpleemail.model.ListReceiptFiltersResult;
import com.amazonaws.services.simpleemail.model.ListReceiptRuleSetsRequest;
import com.amazonaws.services.simpleemail.model.ListReceiptRuleSetsResult;
import com.amazonaws.services.simpleemail.model.ListVerifiedEmailAddressesRequest;
import com.amazonaws.services.simpleemail.model.ListVerifiedEmailAddressesResult;
import com.amazonaws.services.simpleemail.model.PutIdentityPolicyRequest;
import com.amazonaws.services.simpleemail.model.PutIdentityPolicyResult;
import com.amazonaws.services.simpleemail.model.ReorderReceiptRuleSetRequest;
import com.amazonaws.services.simpleemail.model.ReorderReceiptRuleSetResult;
import com.amazonaws.services.simpleemail.model.SendBounceRequest;
import com.amazonaws.services.simpleemail.model.SendBounceResult;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.amazonaws.services.simpleemail.model.SendEmailResult;
import com.amazonaws.services.simpleemail.model.SendRawEmailRequest;
import com.amazonaws.services.simpleemail.model.SendRawEmailResult;
import com.amazonaws.services.simpleemail.model.SetActiveReceiptRuleSetRequest;
import com.amazonaws.services.simpleemail.model.SetActiveReceiptRuleSetResult;
import com.amazonaws.services.simpleemail.model.SetIdentityDkimEnabledRequest;
import com.amazonaws.services.simpleemail.model.SetIdentityDkimEnabledResult;
import com.amazonaws.services.simpleemail.model.SetIdentityFeedbackForwardingEnabledRequest;
import com.amazonaws.services.simpleemail.model.SetIdentityFeedbackForwardingEnabledResult;
import com.amazonaws.services.simpleemail.model.SetIdentityHeadersInNotificationsEnabledRequest;
import com.amazonaws.services.simpleemail.model.SetIdentityHeadersInNotificationsEnabledResult;
import com.amazonaws.services.simpleemail.model.SetIdentityMailFromDomainRequest;
import com.amazonaws.services.simpleemail.model.SetIdentityMailFromDomainResult;
import com.amazonaws.services.simpleemail.model.SetIdentityNotificationTopicRequest;
import com.amazonaws.services.simpleemail.model.SetIdentityNotificationTopicResult;
import com.amazonaws.services.simpleemail.model.SetReceiptRulePositionRequest;
import com.amazonaws.services.simpleemail.model.SetReceiptRulePositionResult;
import com.amazonaws.services.simpleemail.model.UpdateConfigurationSetEventDestinationRequest;
import com.amazonaws.services.simpleemail.model.UpdateConfigurationSetEventDestinationResult;
import com.amazonaws.services.simpleemail.model.UpdateReceiptRuleRequest;
import com.amazonaws.services.simpleemail.model.UpdateReceiptRuleResult;
import com.amazonaws.services.simpleemail.model.VerifyDomainDkimRequest;
import com.amazonaws.services.simpleemail.model.VerifyDomainDkimResult;
import com.amazonaws.services.simpleemail.model.VerifyDomainIdentityRequest;
import com.amazonaws.services.simpleemail.model.VerifyDomainIdentityResult;
import com.amazonaws.services.simpleemail.model.VerifyEmailAddressRequest;
import com.amazonaws.services.simpleemail.model.VerifyEmailAddressResult;
import com.amazonaws.services.simpleemail.model.VerifyEmailIdentityRequest;
import com.amazonaws.services.simpleemail.model.VerifyEmailIdentityResult;
import com.amazonaws.services.simpleemail.waiters.AmazonSimpleEmailServiceWaiters;

public class MockAmazonSimpleEmailService implements AmazonSimpleEmailService {

    @Override
    public void setEndpoint(String s) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public void setRegion(Region region) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public CloneReceiptRuleSetResult cloneReceiptRuleSet(
        CloneReceiptRuleSetRequest cloneReceiptRuleSetRequest) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public CreateConfigurationSetResult createConfigurationSet(
        CreateConfigurationSetRequest createConfigurationSetRequest) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public CreateConfigurationSetEventDestinationResult createConfigurationSetEventDestination(
        CreateConfigurationSetEventDestinationRequest createConfigurationSetEventDestinationRequest) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public CreateReceiptFilterResult createReceiptFilter(
        CreateReceiptFilterRequest createReceiptFilterRequest) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public CreateReceiptRuleResult createReceiptRule(
        CreateReceiptRuleRequest createReceiptRuleRequest) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public CreateReceiptRuleSetResult createReceiptRuleSet(
        CreateReceiptRuleSetRequest createReceiptRuleSetRequest) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public DeleteConfigurationSetResult deleteConfigurationSet(
        DeleteConfigurationSetRequest deleteConfigurationSetRequest) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public DeleteConfigurationSetEventDestinationResult deleteConfigurationSetEventDestination(
        DeleteConfigurationSetEventDestinationRequest deleteConfigurationSetEventDestinationRequest) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public DeleteIdentityResult deleteIdentity(DeleteIdentityRequest deleteIdentityRequest) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public DeleteIdentityPolicyResult deleteIdentityPolicy(
        DeleteIdentityPolicyRequest deleteIdentityPolicyRequest) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public DeleteReceiptFilterResult deleteReceiptFilter(
        DeleteReceiptFilterRequest deleteReceiptFilterRequest) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public DeleteReceiptRuleResult deleteReceiptRule(
        DeleteReceiptRuleRequest deleteReceiptRuleRequest) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public DeleteReceiptRuleSetResult deleteReceiptRuleSet(
        DeleteReceiptRuleSetRequest deleteReceiptRuleSetRequest) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public DeleteVerifiedEmailAddressResult deleteVerifiedEmailAddress(
        DeleteVerifiedEmailAddressRequest deleteVerifiedEmailAddressRequest) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public DescribeActiveReceiptRuleSetResult describeActiveReceiptRuleSet(
        DescribeActiveReceiptRuleSetRequest describeActiveReceiptRuleSetRequest) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public DescribeConfigurationSetResult describeConfigurationSet(
        DescribeConfigurationSetRequest describeConfigurationSetRequest) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public DescribeReceiptRuleResult describeReceiptRule(
        DescribeReceiptRuleRequest describeReceiptRuleRequest) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public DescribeReceiptRuleSetResult describeReceiptRuleSet(
        DescribeReceiptRuleSetRequest describeReceiptRuleSetRequest) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public GetIdentityDkimAttributesResult getIdentityDkimAttributes(
        GetIdentityDkimAttributesRequest getIdentityDkimAttributesRequest) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public GetIdentityMailFromDomainAttributesResult getIdentityMailFromDomainAttributes(
        GetIdentityMailFromDomainAttributesRequest getIdentityMailFromDomainAttributesRequest) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public GetIdentityNotificationAttributesResult getIdentityNotificationAttributes(
        GetIdentityNotificationAttributesRequest getIdentityNotificationAttributesRequest) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public GetIdentityPoliciesResult getIdentityPolicies(
        GetIdentityPoliciesRequest getIdentityPoliciesRequest) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public GetIdentityVerificationAttributesResult getIdentityVerificationAttributes(
        GetIdentityVerificationAttributesRequest getIdentityVerificationAttributesRequest) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public GetSendQuotaResult getSendQuota(GetSendQuotaRequest getSendQuotaRequest) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public GetSendQuotaResult getSendQuota() {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public GetSendStatisticsResult getSendStatistics(
        GetSendStatisticsRequest getSendStatisticsRequest) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public GetSendStatisticsResult getSendStatistics() {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public ListConfigurationSetsResult listConfigurationSets(
        ListConfigurationSetsRequest listConfigurationSetsRequest) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public ListIdentitiesResult listIdentities(ListIdentitiesRequest listIdentitiesRequest) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public ListIdentitiesResult listIdentities() {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public ListIdentityPoliciesResult listIdentityPolicies(
        ListIdentityPoliciesRequest listIdentityPoliciesRequest) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public ListReceiptFiltersResult listReceiptFilters(
        ListReceiptFiltersRequest listReceiptFiltersRequest) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public ListReceiptRuleSetsResult listReceiptRuleSets(
        ListReceiptRuleSetsRequest listReceiptRuleSetsRequest) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public ListVerifiedEmailAddressesResult listVerifiedEmailAddresses(
        ListVerifiedEmailAddressesRequest listVerifiedEmailAddressesRequest) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public ListVerifiedEmailAddressesResult listVerifiedEmailAddresses() {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public PutIdentityPolicyResult putIdentityPolicy(
        PutIdentityPolicyRequest putIdentityPolicyRequest) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public ReorderReceiptRuleSetResult reorderReceiptRuleSet(
        ReorderReceiptRuleSetRequest reorderReceiptRuleSetRequest) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public SendBounceResult sendBounce(SendBounceRequest sendBounceRequest) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public SendEmailResult sendEmail(SendEmailRequest sendEmailRequest) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public SendRawEmailResult sendRawEmail(SendRawEmailRequest sendRawEmailRequest) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public SetActiveReceiptRuleSetResult setActiveReceiptRuleSet(
        SetActiveReceiptRuleSetRequest setActiveReceiptRuleSetRequest) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public SetIdentityDkimEnabledResult setIdentityDkimEnabled(
        SetIdentityDkimEnabledRequest setIdentityDkimEnabledRequest) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public SetIdentityFeedbackForwardingEnabledResult setIdentityFeedbackForwardingEnabled(
        SetIdentityFeedbackForwardingEnabledRequest setIdentityFeedbackForwardingEnabledRequest) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public SetIdentityHeadersInNotificationsEnabledResult setIdentityHeadersInNotificationsEnabled(
        SetIdentityHeadersInNotificationsEnabledRequest setIdentityHeadersInNotificationsEnabledRequest) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public SetIdentityMailFromDomainResult setIdentityMailFromDomain(
        SetIdentityMailFromDomainRequest setIdentityMailFromDomainRequest) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public SetIdentityNotificationTopicResult setIdentityNotificationTopic(
        SetIdentityNotificationTopicRequest setIdentityNotificationTopicRequest) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public SetReceiptRulePositionResult setReceiptRulePosition(
        SetReceiptRulePositionRequest setReceiptRulePositionRequest) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public UpdateConfigurationSetEventDestinationResult updateConfigurationSetEventDestination(
        UpdateConfigurationSetEventDestinationRequest updateConfigurationSetEventDestinationRequest) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public UpdateReceiptRuleResult updateReceiptRule(
        UpdateReceiptRuleRequest updateReceiptRuleRequest) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public VerifyDomainDkimResult verifyDomainDkim(
        VerifyDomainDkimRequest verifyDomainDkimRequest) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public VerifyDomainIdentityResult verifyDomainIdentity(
        VerifyDomainIdentityRequest verifyDomainIdentityRequest) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public VerifyEmailAddressResult verifyEmailAddress(
        VerifyEmailAddressRequest verifyEmailAddressRequest) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    @Override
    public VerifyEmailIdentityResult verifyEmailIdentity(
        VerifyEmailIdentityRequest verifyEmailIdentityRequest) {
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

    @Override
    public AmazonSimpleEmailServiceWaiters waiters() {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }
}
