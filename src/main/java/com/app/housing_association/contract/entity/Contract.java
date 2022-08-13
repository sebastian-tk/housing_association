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

   @OneToOne()
   @JoinColumn(name = "user_id")
   private User user;

   @OneToOne()
   @JoinColumn(name = "flat_id")
   private Flat flat;

   @OneToOne(cascade = CascadeType.ALL)
   @JoinColumn(name = "fee_id")
   private Fee fee;

   @PrePersist
   public void createStartTime() {
      this.startTime = Instant.now();
   }

   @PreRemove
   public void makeFreeFlat() {
      this.flat.setAvailable(true);
   }
}
