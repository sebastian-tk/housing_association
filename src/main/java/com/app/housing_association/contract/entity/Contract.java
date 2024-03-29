package com.app.housing_association.contract.entity;

import com.app.housing_association.common.model.BaseEntity;
import com.app.housing_association.contract.entity.enums.ContractType;
import com.app.housing_association.fee.entity.Fee;
import com.app.housing_association.flat.entity.Flat;
import com.app.housing_association.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.Instant;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "contracts")
public class Contract extends BaseEntity<Long> {

   @NotNull
   @Enumerated(EnumType.STRING)
   private ContractType type;

   @NotNull
   @Min(1)
   @Column(name = "amount_people")
   private Integer amountPeople;

   @NotNull
   @Column(name = "start_time")
   private Instant startTime;

   @Column(name = "finish_time")
   private Instant finishTime;

   @OneToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH})
   @JoinColumn(name = "user_id")
   private User user;

   @OneToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH})
   @JoinColumn(name = "flat_id")
   private Flat flat;

   @OneToOne(mappedBy = "contract",cascade = {CascadeType.ALL})
   private Fee fee;

   @PrePersist
   public void createStartTime() {
      this.startTime = Instant.now();
   }

   @PreRemove
   public void makeFreeFlat() {
      if(nonNull(this.flat)){
         this.flat.setAvailable(true);
      }
   }

   public void setFee(Fee fee) {
      if (isNull(fee)) {
         if (nonNull(this.fee)) {
            this.fee = null;
         }
      }
      {
         fee.setContract(this);
      }
      this.fee = fee;
   }
}
